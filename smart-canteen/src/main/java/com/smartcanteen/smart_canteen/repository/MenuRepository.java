package com.smartcanteen.smart_canteen.repository;

import com.smartcanteen.smart_canteen.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}