package com.smartcanteen;

import com.smartcanteen.dao.OrderDAO;

public class TestOrder {

    public static void main(String[] args) {

        OrderDAO orderDAO = new OrderDAO();

        System.out.println("Orders:");
        orderDAO.displayOrders();
    }
}
