package com.smartcanteen;

import com.smartcanteen.dao.OrderDetailsDAO;

public class TestOrderDetails {

    public static void main(String[] args) {

        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();

        System.out.println("Order Details:");
        orderDetailsDAO.displayOrderDetails();
    }
}
