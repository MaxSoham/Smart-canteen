package com.smartcanteen;

import com.smartcanteen.dao.FoodItemDAO;

public class FoodMenu {

    public static void main(String[] args) {

        System.out.println("===== SMART CANTEEN =====");
        System.out.println("FOOD MENU");
        System.out.println("--------------------------------");

        FoodItemDAO foodItemDAO = new FoodItemDAO();

        foodItemDAO.displayFoodItems();

        System.out.println("--------------------------------");
        System.out.println("Menu Display Completed!");
    }
}