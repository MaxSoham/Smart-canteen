package com.smartcanteen;

import com.smartcanteen.dao.PaymentDAO;
import com.smartcanteen.model.Payment;

import java.util.Scanner;

public class AddPayment {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== SMART CANTEEN =====");
        System.out.println("PAYMENT");

        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Payment Method: ");
        String paymentMethod = scanner.nextLine();

        System.out.print("Enter Payment Status: ");
        String paymentStatus = scanner.nextLine();

        Payment payment = new Payment();

        payment.setOrderId(orderId);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus(paymentStatus);

        PaymentDAO paymentDAO = new PaymentDAO();

        boolean result = paymentDAO.addPayment(payment);

        if (result) {
            System.out.println("\nPayment Added Successfully!");
        } else {
            System.out.println("\nPayment Addition Failed!");
        }

        scanner.close();
    }
}
