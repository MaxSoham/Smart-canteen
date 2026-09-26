package com.smartcanteen.smart_canteen.repository;

import com.smartcanteen.smart_canteen.entity.Order;
import com.smartcanteen.smart_canteen.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrder(Order order);
}