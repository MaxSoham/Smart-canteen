package com.smartcanteen.controller;

import com.smartcanteen.DBConnection;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin
public class OrderController {

    // =========================
    // CREATE ORDER
    // =========================

    @PostMapping("/create")
    public String createOrder(@RequestBody OrderRequest request) {

        Connection connection = null;

        try {

            connection = DBConnection.getConnection();

            connection.setAutoCommit(false);

            String orderSql =
                    "INSERT INTO orders " +
                            "(customer_id, order_date, total_amount, order_status) " +
                            "VALUES (?, CURRENT_TIMESTAMP, ?, 'Pending') " +
                            "RETURNING order_id";

            PreparedStatement orderStatement =
                    connection.prepareStatement(orderSql);

            orderStatement.setString(
                    1,
                    request.getStudentId()
            );

            orderStatement.setDouble(
                    2,
                    request.getTotalAmount()
            );

            ResultSet resultSet =
                    orderStatement.executeQuery();

            int orderId = 0;

            if (resultSet.next()) {
                orderId =
                        resultSet.getInt("order_id");
            }

            if (orderId == 0) {

                connection.rollback();

                return "Order placement failed!";
            }

            String detailSql =
                    "INSERT INTO order_details " +
                            "(order_id, food_item_id, quantity, price) " +
                            "VALUES (?, ?, ?, ?)";

            PreparedStatement detailStatement =
                    connection.prepareStatement(detailSql);

            for (CartItem item : request.getItems()) {

                detailStatement.setInt(
                        1,
                        orderId
                );

                detailStatement.setInt(
                        2,
                        item.getFoodItemId()
                );

                detailStatement.setInt(
                        3,
                        item.getQuantity()
                );

                detailStatement.setDouble(
                        4,
                        item.getPrice()
                );

                detailStatement.executeUpdate();
            }

            connection.commit();

            return "Order placed successfully! Order ID: "
                    + orderId;

        } catch (Exception e) {

            e.printStackTrace();

            try {

                if (connection != null) {
                    connection.rollback();
                }

            } catch (Exception rollbackError) {

                rollbackError.printStackTrace();
            }

            return "Order placement failed!";

        } finally {

            try {

                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }


    // =========================
    // UPDATE ORDER STATUS
    // =========================

    @PutMapping("/status")
    public String updateOrderStatus(
            @RequestBody StatusRequest request) {

        String sql =
                "UPDATE orders " +
                        "SET order_status = ? " +
                        "WHERE order_id = ?";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    request.getOrderStatus()
            );

            statement.setInt(
                    2,
                    request.getOrderId()
            );

            int rows =
                    statement.executeUpdate();

            if (rows > 0) {
                return "Order status updated successfully!";
            }

            return "Order not found!";

        } catch (Exception e) {

            e.printStackTrace();

            return "Failed to update order status!";
        }
    }


    // =========================
    // GET ALL ORDERS
    // =========================

    @GetMapping("/all")
    public List<AllOrderResponse> getAllOrders() {

        List<AllOrderResponse> orders =
                new ArrayList<>();

        String sql =
                "SELECT order_id, customer_id, order_date, " +
                        "total_amount, order_status " +
                        "FROM orders " +
                        "ORDER BY order_id DESC";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                AllOrderResponse order =
                        new AllOrderResponse();

                order.setOrderId(
                        resultSet.getInt("order_id")
                );

                order.setStudentId(
                        resultSet.getString("customer_id")
                );

                order.setOrderDate(
                        resultSet.getTimestamp("order_date")
                                .toString()
                );

                order.setTotalAmount(
                        resultSet.getDouble("total_amount")
                );

                order.setOrderStatus(
                        resultSet.getString("order_status")
                );

                orders.add(order);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return orders;
    }


    // =========================
    // STATUS REQUEST
    // =========================

    public static class StatusRequest {

        private int orderId;

        private String orderStatus;


        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }


        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }
    }


    // =========================
    // ORDER REQUEST
    // =========================

    public static class OrderRequest {

        private String studentId;

        private double totalAmount;

        private CartItem[] items;


        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }


        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }


        public CartItem[] getItems() {
            return items;
        }

        public void setItems(CartItem[] items) {
            this.items = items;
        }
    }


    // =========================
    // CART ITEM
    // =========================

    public static class CartItem {

        private int foodItemId;

        private String name;

        private double price;

        private int quantity;


        public int getFoodItemId() {
            return foodItemId;
        }

        public void setFoodItemId(int foodItemId) {
            this.foodItemId = foodItemId;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }


        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }


    // =========================
    // ALL ORDER RESPONSE
    // =========================

    public static class AllOrderResponse {

        private int orderId;

        private String studentId;

        private String orderDate;

        private double totalAmount;

        private String orderStatus;


        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }


        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }


        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }


        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }


        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }
    }
}

