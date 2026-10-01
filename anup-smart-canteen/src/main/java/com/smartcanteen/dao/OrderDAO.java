package com.smartcanteen.dao;

import com.smartcanteen.DBConnection;
import com.smartcanteen.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO {

    // Create Order
    public boolean createOrder(Order order) {

        String sql = "INSERT INTO orders " +
                "(customer_id, order_date, total_amount, order_status) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, order.getCustomerId());
            statement.setTimestamp(2, order.getOrderDate());
            statement.setDouble(3, order.getTotalAmount());
            statement.setString(4, order.getOrderStatus());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Order creation failed!");
            e.printStackTrace();
            return false;
        }
    }

    // Display Orders
    public void displayOrders() {

        String sql = "SELECT * FROM orders";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                System.out.println(
                        resultSet.getInt("order_id") + " | " +
                                resultSet.getString("customer_id") + " | " +
                                resultSet.getTimestamp("order_date") + " | " +
                                resultSet.getDouble("total_amount") + " | " +
                                resultSet.getString("order_status")
                );
            }

        } catch (SQLException e) {
            System.out.println("Order display failed!");
            e.printStackTrace();
        }
    }
}