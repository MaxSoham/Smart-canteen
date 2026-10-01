package com.smartcanteen.controller;

import com.smartcanteen.DBConnection;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin
public class PaymentController {

    @PostMapping("/add")
    public String addPayment(@RequestBody PaymentRequest request) {

        String sql =
                "INSERT INTO payment " +
                        "(order_id, payment_method, payment_status) " +
                        "VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    request.getOrderId()
            );

            statement.setString(
                    2,
                    request.getPaymentMethod()
            );

            statement.setString(
                    3,
                    request.getPaymentStatus()
            );

            int rows =
                    statement.executeUpdate();

            if (rows > 0) {

                return "Payment Successful!";

            }

            return "Payment Failed!";

        } catch (Exception e) {

            e.printStackTrace();

            return "Payment Failed!";

        }
    }


    // PAYMENT REQUEST

    public static class PaymentRequest {

        private int orderId;

        private String paymentMethod;

        private String paymentStatus;


        public int getOrderId() {

            return orderId;

        }


        public void setOrderId(int orderId) {

            this.orderId = orderId;

        }


        public String getPaymentMethod() {

            return paymentMethod;

        }


        public void setPaymentMethod(
                String paymentMethod) {

            this.paymentMethod =
                    paymentMethod;

        }


        public String getPaymentStatus() {

            return paymentStatus;

        }


        public void setPaymentStatus(
                String paymentStatus) {

            this.paymentStatus =
                    paymentStatus;

        }

    }

}


