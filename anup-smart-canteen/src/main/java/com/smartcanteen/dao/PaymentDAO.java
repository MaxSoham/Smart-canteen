package com.smartcanteen.dao;

import com.smartcanteen.DBConnection;
import com.smartcanteen.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO {

    // Add Payment
    public boolean addPayment(Payment payment) {

        String sql = "INSERT INTO payment " +
                "(order_id, payment_method, payment_status) " +
                "VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, payment.getOrderId());
            statement.setString(2, payment.getPaymentMethod());
            statement.setString(3, payment.getPaymentStatus());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Payment insertion failed!");
            e.printStackTrace();
            return false;
        }
    }

    // Display Payments
    public void displayPayments() {

        String sql = "SELECT * FROM payment";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                System.out.println(
                        resultSet.getInt("payment_id") + " | " +
                                resultSet.getInt("order_id") + " | " +
                                resultSet.getString("payment_method") + " | " +
                                resultSet.getString("payment_status") + " | " +
                                resultSet.getTimestamp("payment_date")
                );
            }

        } catch (SQLException e) {
            System.out.println("Payment display failed!");
            e.printStackTrace();
        }
    }
}
