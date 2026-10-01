package com.smartcanteen;

import com.smartcanteen.dao.OrderDAO;
import com.smartcanteen.model.Order;

import java.sql.Timestamp;
import java.util.Scanner;

public class CreateOrder {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== SMART CANTEEN =====");
        System.out.println("CREATE ORDER");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter Total Amount: ");
        double totalAmount = scanner.nextDouble();
        scanner.nextLine();

        Order order = new Order();

        order.setCustomerId(studentId);
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmount(totalAmount);
        order.setOrderStatus("Pending");

        OrderDAO orderDAO = new OrderDAO();

        boolean result = orderDAO.createOrder(order);

        if (result) {
            System.out.println("\nOrder Created Successfully!");
        } else {
            System.out.println("\nOrder Creation Failed!");
        }

        scanner.close();
    }
}