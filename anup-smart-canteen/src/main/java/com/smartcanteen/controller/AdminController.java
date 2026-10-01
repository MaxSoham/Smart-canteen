package com.smartcanteen.controller;

import com.smartcanteen.dao.AdminDAO;
import com.smartcanteen.model.Admin;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private final AdminDAO adminDAO = new AdminDAO();

    @PostMapping("/login")
    public String loginAdmin(
            @RequestBody AdminLoginRequest request) {

        Admin admin = adminDAO.loginAdmin(
                request.getEmail(),
                request.getPassword()
        );

        if (admin != null) {
            return "SUCCESS";
        }

        return "Invalid Email or Password!";
    }

    public static class AdminLoginRequest {

        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}

