package com.smartcanteen.controller;

import com.smartcanteen.DBConnection;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

@RestController
@RequestMapping("/api/admin/food")
@CrossOrigin
public class FoodAdminController {

    // =========================
    // ADD FOOD
    // =========================

    @PostMapping("/add")
    public String addFood(@RequestBody FoodRequest food) {

        String sql =
                "INSERT INTO food_item " +
                        "(category_id, food_name, description, price, availability) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection =
                     DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    food.getCategoryId()
            );

            statement.setString(
                    2,
                    food.getFoodName()
            );

            statement.setString(
                    3,
                    food.getDescription()
            );

            statement.setDouble(
                    4,
                    food.getPrice()
            );

            statement.setBoolean(
                    5,
                    food.isAvailability()
            );

            statement.executeUpdate();

            return "Food added successfully!";

        } catch (Exception e) {

            e.printStackTrace();

            return "Failed to add food!";
        }
    }


    // =========================
    // DELETE FOOD
    // =========================

    @DeleteMapping("/{foodItemId}")
    public String deleteFood(
            @PathVariable int foodItemId) {

        String sql =
                "DELETE FROM food_item " +
                        "WHERE food_item_id = ?";

        try (Connection connection =
                     DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    foodItemId
            );

            int rows =
                    statement.executeUpdate();

            if (rows > 0) {

                return "Food deleted successfully!";

            }

            return "Food not found!";

        } catch (Exception e) {

            e.printStackTrace();

            return "Failed to delete food!";
        }
    }


    // =========================
    // UPDATE AVAILABILITY
    // =========================

    @PutMapping("/{foodItemId}/availability")
    public String updateAvailability(
            @PathVariable int foodItemId,
            @RequestParam boolean availability) {

        String sql =
                "UPDATE food_item " +
                        "SET availability = ? " +
                        "WHERE food_item_id = ?";

        try (Connection connection =
                     DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setBoolean(
                    1,
                    availability
            );

            statement.setInt(
                    2,
                    foodItemId
            );

            int rows =
                    statement.executeUpdate();

            if (rows > 0) {

                return "Availability updated successfully!";

            }

            return "Food not found!";

        } catch (Exception e) {

            e.printStackTrace();

            return "Failed to update availability!";
        }
    }


    // =========================
    // FOOD REQUEST CLASS
    // =========================

    public static class FoodRequest {

        private int categoryId;

        private String foodName;

        private String description;

        private double price;

        private boolean availability;


        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId =
                    categoryId;
        }


        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName =
                    foodName;
        }


        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description =
                    description;
        }


        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price =
                    price;
        }


        public boolean isAvailability() {
            return availability;
        }

        public void setAvailability(
                boolean availability) {

            this.availability =
                    availability;
        }
    }
}

