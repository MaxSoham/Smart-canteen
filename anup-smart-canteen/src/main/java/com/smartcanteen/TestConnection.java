package com.smartcanteen;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();

        if (connection != null) {
            System.out.println("Smart Canteen Database is Ready!");
        } else {
            System.out.println("Connection Failed!");
        }
    }
}

