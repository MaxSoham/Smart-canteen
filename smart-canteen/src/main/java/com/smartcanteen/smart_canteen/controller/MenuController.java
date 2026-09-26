package com.smartcanteen.smart_canteen.controller;

import com.smartcanteen.smart_canteen.entity.Menu;
import com.smartcanteen.smart_canteen.repository.MenuRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping
    public List<Menu> getMenu() {
        return menuRepository.findAll();
    }
}