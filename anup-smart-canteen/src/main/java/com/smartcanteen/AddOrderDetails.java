package com.smartcanteen;

import com.smartcanteen.dao.OrderDetailsDAO;
import com.smartcanteen.model.OrderDetails;

import java.util.Scanner;

public class AddOrderDetails {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== SMART CANTEEN =====");
        System.out.println("ADD ORDER DETAILS");

        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();

        System.out.print("Enter Food Item ID: ");
        int foodItemId = scanner.nextInt();

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        OrderDetails orderDetails = new OrderDetails();

        orderDetails.setOrderId(orderId);
        orderDetails.setFoodItemId(foodItemId);
        orderDetails.setQuantity(quantity);
        orderDetails.setPrice(price);

        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();

        boolean result = orderDetailsDAO.addOrderDetails(orderDetails);

        if (result) {
            System.out.println("\nOrder Details Added Successfully!");
        } else {
            System.out.println("\nOrder Details Addition Failed!");
        }

        scanner.close();
    }
}