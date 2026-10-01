package com.smartcanteen;

import com.smartcanteen.dao.FoodItemDAO;

public class TestFoodItem {

    public static void main(String[] args) {

        FoodItemDAO foodItemDAO = new FoodItemDAO();

        System.out.println("Food Items:");
        foodItemDAO.displayFoodItems();
    }
}
