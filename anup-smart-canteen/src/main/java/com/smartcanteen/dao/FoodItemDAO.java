package com.smartcanteen.dao;

import com.smartcanteen.DBConnection;
import com.smartcanteen.model.FoodItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodItemDAO {

    // Add Food Item
    public boolean addFoodItem(FoodItem foodItem) {

        String sql = "INSERT INTO food_item " +
                "(category_id, food_name, description, price, availability) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, foodItem.getCategoryId());
            statement.setString(2, foodItem.getFoodName());
            statement.setString(3, foodItem.getDescription());
            statement.setDouble(4, foodItem.getPrice());
            statement.setBoolean(5, foodItem.isAvailability());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Food item insertion failed!");
            e.printStackTrace();
            return false;
        }
    }

    // Display Food Items
    public void displayFoodItems() {

        String sql = "SELECT * FROM food_item";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                System.out.println(
                        resultSet.getInt("food_item_id") + " | " +
                                resultSet.getInt("category_id") + " | " +
                                resultSet.getString("food_name") + " | " +
                                resultSet.getString("description") + " | " +
                                resultSet.getDouble("price") + " | " +
                                resultSet.getBoolean("availability")
                );
            }

        } catch (SQLException e) {
            System.out.println("Food item display failed!");
            e.printStackTrace();
        }
    }
}
