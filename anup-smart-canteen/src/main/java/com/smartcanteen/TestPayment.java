package com.smartcanteen;

import com.smartcanteen.dao.PaymentDAO;

public class TestPayment {

    public static void main(String[] args) {

        PaymentDAO paymentDAO = new PaymentDAO();

        System.out.println("Payments:");
        paymentDAO.displayPayments();
    }
}
