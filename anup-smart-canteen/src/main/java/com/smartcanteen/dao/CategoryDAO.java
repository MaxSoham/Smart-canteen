package com.smartcanteen.dao;

import com.smartcanteen.DBConnection;
import com.smartcanteen.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAO {

    // Add Category
    public boolean addCategory(Category category) {

        String sql = "INSERT INTO category (category_name, description) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, category.getCategoryName());
            statement.setString(2, category.getDescription());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Category insertion failed!");
            e.printStackTrace();
            return false;
        }
    }

    // Display Categories
    public void displayCategories() {

        String sql = "SELECT * FROM category";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                System.out.println(
                        resultSet.getInt("category_id") + " | " +
                                resultSet.getString("category_name") + " | " +
                                resultSet.getString("description")
                );
            }

        } catch (SQLException e) {
            System.out.println("Category display failed!");
            e.printStackTrace();
        }
    }
}
