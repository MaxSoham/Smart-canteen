package com.smartcanteen.smart_canteen.controller;

import com.smartcanteen.smart_canteen.entity.Order;
import com.smartcanteen.smart_canteen.entity.User;
import com.smartcanteen.smart_canteen.repository.OrderRepository;
import com.smartcanteen.smart_canteen.repository.UserRepository;
import com.smartcanteen.smart_canteen.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public OrderController(OrderRepository orderRepository,
                           UserRepository userRepository,
                           JwtService jwtService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestHeader(value = "Authorization", required = false)
            String authHeader,
            @RequestBody Order order) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401)
                    .body("Missing or invalid Authorization header");
        }

        try {
            String token = authHeader.substring(7);

            String email = jwtService.extractEmail(token);

            User user = userRepository.findByEmail(email)
                    .orElse(null);

            if (user == null) {
                return ResponseEntity.status(401)
                        .body("User not found");
            }

            order.setUser(user);
            order.setOrderDate(LocalDateTime.now());

            if (order.getStatus() == null) {
                order.setStatus("Pending");
            }

            Order savedOrder = orderRepository.save(order);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Order created successfully");
            response.put("orderId", savedOrder.getOrderId());
            response.put("totalAmount", savedOrder.getTotalAmount());
            response.put("status", savedOrder.getStatus());
            response.put("orderDate", savedOrder.getOrderDate());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(401)
                    .body("Invalid or expired token");
        }
    }

    @GetMapping("/my-orders")
    public ResponseEntity<?> getMyOrders(
            @RequestHeader(value = "Authorization", required = false)
            String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401)
                    .body("Missing or invalid Authorization header");
        }

        try {
            String token = authHeader.substring(7);

            String email = jwtService.extractEmail(token);

            User user = userRepository.findByEmail(email)
                    .orElse(null);

            if (user == null) {
                return ResponseEntity.status(401)
                        .body("User not found");
            }

            List<Order> orders = orderRepository.findByUser(user);

            return ResponseEntity.ok(orders);

        } catch (Exception e) {
            return ResponseEntity.status(401)
                    .body("Invalid or expired token");
        }
    }
}