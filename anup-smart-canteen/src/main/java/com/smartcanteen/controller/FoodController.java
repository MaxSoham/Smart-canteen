
        package com.smartcanteen.controller;

import com.smartcanteen.DBConnection;
import com.smartcanteen.model.FoodItem;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/food")
@CrossOrigin
public class FoodController {


    // STUDENT MENU - AVAILABLE FOOD ONLY

    @GetMapping
    public List<FoodItem> getFoodItems() {

        return getFoodList(
                "WHERE f.availability = true"
        );
    }


    // ADMIN - ALL FOOD ITEMS

    @GetMapping("/all")
    public List<FoodItem> getAllFoodItems() {

        return getFoodList("");
    }


    // COMMON METHOD

    private List<FoodItem> getFoodList(
            String condition) {

        List<FoodItem> foodItems =
                new ArrayList<>();


        String sql =
                "SELECT f.food_item_id, " +
                        "f.category_id, " +
                        "c.category_name, " +
                        "f.food_name, " +
                        "f.description, " +
                        "f.price, " +
                        "f.availability " +
                        "FROM food_item f " +
                        "LEFT JOIN category c " +
                        "ON f.category_id = c.category_id " +
                        condition +
                        " ORDER BY f.food_item_id";


        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {


            while (resultSet.next()) {

                FoodItem foodItem =
                        new FoodItem();


                foodItem.setFoodItemId(
                        resultSet.getInt(
                                "food_item_id"
                        )
                );


                foodItem.setCategoryId(
                        resultSet.getInt(
                                "category_id"
                        )
                );


                foodItem.setCategoryName(
                        resultSet.getString(
                                "category_name"
                        )
                );


                foodItem.setFoodName(
                        resultSet.getString(
                                "food_name"
                        )
                );


                foodItem.setDescription(
                        resultSet.getString(
                                "description"
                        )
                );


                foodItem.setPrice(
                        resultSet.getDouble(
                                "price"
                        )
                );


                foodItem.setAvailability(
                        resultSet.getBoolean(
                                "availability"
                        )
                );


                foodItems.add(foodItem);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return foodItems;
    }



    // ADD FOOD

    @PostMapping("/add")
    public String addFood(
            @RequestBody FoodRequest request) {


        String sql =
                "INSERT INTO food_item " +
                        "(category_id, food_name, description, price, availability) " +
                        "VALUES (?, ?, ?, ?, ?)";


        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {


            statement.setInt(
                    1,
                    request.getCategoryId()
            );


            statement.setString(
                    2,
                    request.getFoodName()
            );


            statement.setString(
                    3,
                    request.getDescription()
            );


            statement.setDouble(
                    4,
                    request.getPrice()
            );


            statement.setBoolean(
                    5,
                    request.isAvailability()
            );


            int rows =
                    statement.executeUpdate();


            if (rows > 0) {

                return "Food item added successfully!";
            }


            return "Failed to add food item!";


        } catch (Exception e) {

            e.printStackTrace();

            return "Failed to add food item!";
        }
    }



    // UPDATE FOOD

    @PutMapping("/update/{id}")
    public String updateFood(
            @PathVariable int id,
            @RequestBody FoodRequest request) {


        String sql =
                "UPDATE food_item SET " +
                        "category_id = ?, " +
                        "food_name = ?, " +
                        "description = ?, " +
                        "price = ?, " +
                        "availability = ? " +
                        "WHERE food_item_id = ?";


        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {


            statement.setInt(
                    1,
                    request.getCategoryId()
            );


            statement.setString(
                    2,
                    request.getFoodName()
            );


            statement.setString(
                    3,
                    request.getDescription()
            );


            statement.setDouble(
                    4,
                    request.getPrice()
            );


            statement.setBoolean(
                    5,
                    request.isAvailability()
            );


            statement.setInt(
                    6,
                    id
            );


            int rows =
                    statement.executeUpdate();


            if (rows > 0) {

                return "Food item updated successfully!";
            }


            return "Food item not found!";


        } catch (Exception e) {

            e.printStackTrace();

            return "Failed to update food item!";
        }
    }



    // DELETE FOOD

    @DeleteMapping("/delete/{id}")
    public String deleteFood(
            @PathVariable int id) {


        String sql =
                "DELETE FROM food_item " +
                        "WHERE food_item_id = ?";


        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {


            statement.setInt(
                    1,
                    id
            );


            int rows =
                    statement.executeUpdate();


            if (rows > 0) {

                return "Food item deleted successfully!";
            }


            return "Food item not found!";


        } catch (Exception e) {

            e.printStackTrace();

            return "Cannot delete food item. " +
                    "It may already be used in an order.";
        }
    }



    // REQUEST CLASS

    public static class FoodRequest {

        private int categoryId;
        private String foodName;
        private String description;
        private double price;
        private boolean availability;


        public int getCategoryId() {

            return categoryId;
        }


        public void setCategoryId(
                int categoryId) {

            this.categoryId =
                    categoryId;
        }


        public String getFoodName() {

            return foodName;
        }


        public void setFoodName(
                String foodName) {

            this.foodName =
                    foodName;
        }


        public String getDescription() {

            return description;
        }


        public void setDescription(
                String description) {

            this.description =
                    description;
        }


        public double getPrice() {

            return price;
        }


        public void setPrice(
                double price) {

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

