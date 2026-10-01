package com.smartcanteen.dao;

import com.smartcanteen.DBConnection;
import com.smartcanteen.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {

    public Admin loginAdmin(String email, String password) {

        String sql =
                "SELECT * FROM admin " +
                        "WHERE email = ? AND password = ?";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return new Admin(
                        resultSet.getInt("admin_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}

