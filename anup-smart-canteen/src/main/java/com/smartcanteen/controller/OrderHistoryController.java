package com.smartcanteen.controller;

import com.smartcanteen.DBConnection;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderHistoryController {

    // Student: View their own orders
    @GetMapping("/{studentId}")
    public List<Order> getOrders(
            @PathVariable String studentId) {

        return getOrdersFromDatabase(
                "WHERE o.customer_id = ?",
                studentId
        );
    }


    // Admin: View ALL orders
    @GetMapping("/all")
    public List<Order> getAllOrders() {

        return getOrdersFromDatabase(
                "",
                null
        );
    }


    private List<Order> getOrdersFromDatabase(
            String condition,
            String studentId) {

        List<Order> orders = new ArrayList<>();

        String sql =
                "SELECT o.order_id, " +
                        "o.customer_id, " +
                        "s.name AS student_name, " +
                        "o.order_date, " +
                        "o.total_amount, " +
                        "o.order_status, " +
                        "p.payment_method, " +
                        "p.payment_status " +
                        "FROM orders o " +
                        "LEFT JOIN student s " +
                        "ON o.customer_id = s.student_id " +
                        "LEFT JOIN payment p " +
                        "ON o.order_id = p.order_id " +
                        condition +
                        " ORDER BY o.order_id DESC";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            if (studentId != null) {
                statement.setString(1, studentId);
            }

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Order order = new Order();

                order.setOrderId(
                        resultSet.getInt("order_id")
                );

                order.setCustomerId(
                        resultSet.getString("customer_id")
                );

                order.setStudentName(
                        resultSet.getString("student_name")
                );

                order.setOrderDate(
                        resultSet.getString("order_date")
                );

                order.setTotalAmount(
                        resultSet.getDouble("total_amount")
                );

                order.setOrderStatus(
                        resultSet.getString("order_status")
                );

                order.setPaymentMethod(
                        resultSet.getString("payment_method")
                );

                order.setPaymentStatus(
                        resultSet.getString("payment_status")
                );

                orders.add(order);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return orders;
    }


    public static class Order {

        private int orderId;
        private String customerId;
        private String studentName;
        private String orderDate;
        private double totalAmount;
        private String orderStatus;
        private String paymentMethod;
        private String paymentStatus;


        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }


        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }


        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
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


        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }


        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }
    }
}

