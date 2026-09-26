package com.smartcanteen.smart_canteen.controller;

import com.smartcanteen.smart_canteen.entity.Menu;
import com.smartcanteen.smart_canteen.entity.Order;
import com.smartcanteen.smart_canteen.entity.OrderItem;
import com.smartcanteen.smart_canteen.repository.MenuRepository;
import com.smartcanteen.smart_canteen.repository.OrderItemRepository;
import com.smartcanteen.smart_canteen.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    public OrderItemController(OrderItemRepository orderItemRepository,
                               OrderRepository orderRepository,
                               MenuRepository menuRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
    }

    @PostMapping
    public ResponseEntity<?> addOrderItem(
            @RequestParam Integer orderId,
            @RequestParam Integer itemId,
            @RequestParam Integer quantity) {

        Order order = orderRepository.findById(orderId)
                .orElse(null);

        if (order == null) {
            return ResponseEntity.badRequest()
                    .body("Order not found");
        }

        Menu menu = menuRepository.findById(itemId)
                .orElse(null);

        if (menu == null) {
            return ResponseEntity.badRequest()
                    .body("Menu item not found");
        }

        if (quantity <= 0) {
            return ResponseEntity.badRequest()
                    .body("Quantity must be greater than 0");
        }
        if (!menu.getAvailability()) {
            return ResponseEntity.badRequest()
                    .body("Menu item is not available");
        }
        OrderItem orderItem = new OrderItem();

        orderItem.setOrder(order);
        orderItem.setMenu(menu);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(menu.getPrice());

        OrderItem savedItem = orderItemRepository.save(orderItem);

        return ResponseEntity.ok(savedItem);
    }
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderItems(@PathVariable Integer orderId) {

        Order order = orderRepository.findById(orderId)
                .orElse(null);

        if (order == null) {
            return ResponseEntity.badRequest()
                    .body("Order not found");
        }

        return ResponseEntity.ok(
                orderItemRepository.findByOrder(order)
        );
    }
}