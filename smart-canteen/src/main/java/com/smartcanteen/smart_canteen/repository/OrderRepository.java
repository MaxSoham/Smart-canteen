package com.smartcanteen.smart_canteen.repository;

import com.smartcanteen.smart_canteen.entity.Order;
import com.smartcanteen.smart_canteen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUser(User user);
}