package com.smartcanteen.dao;

import com.smartcanteen.DBConnection;
import com.smartcanteen.model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsDAO {

    // Add Order Details
    public boolean addOrderDetails(OrderDetails orderDetails) {

        String sql = "INSERT INTO order_details " +
                "(order_id, food_item_id, quantity, price) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, orderDetails.getOrderId());
            statement.setInt(2, orderDetails.getFoodItemId());
            statement.setInt(3, orderDetails.getQuantity());
            statement.setDouble(4, orderDetails.getPrice());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Order details insertion failed!");
            e.printStackTrace();
            return false;
        }
    }

    // Display Order Details
    public void displayOrderDetails() {

        String sql = "SELECT * FROM order_details";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                System.out.println(
                        resultSet.getInt("order_detail_id") + " | " +
                                resultSet.getInt("order_id") + " | " +
                                resultSet.getInt("food_item_id") + " | " +
                                resultSet.getInt("quantity") + " | " +
                                resultSet.getDouble("price")
                );
            }

        } catch (SQLException e) {
            System.out.println("Order details display failed!");
            e.printStackTrace();
        }
    }
}
