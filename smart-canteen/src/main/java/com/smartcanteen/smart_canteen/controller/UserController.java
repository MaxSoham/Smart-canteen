package com.smartcanteen.smart_canteen.controller;

import com.smartcanteen.smart_canteen.entity.User;
import com.smartcanteen.smart_canteen.repository.UserRepository;
import com.smartcanteen.smart_canteen.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // =========================
    // REGISTER
    // =========================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest()
                    .body("Email already registered");
        }

        // Hash password before saving
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        User savedUser = userRepository.save(user);

        // Never return password
        savedUser.setPassword(null);

        return ResponseEntity.ok(savedUser);
    }

    // =========================
    // LOGIN
    // =========================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User existingUser = userRepository
                .findByEmail(user.getEmail())
                .orElse(null);

        if (existingUser == null) {
            return ResponseEntity.badRequest()
                    .body("Invalid email or password");
        }

        // Verify password using BCrypt
        if (!passwordEncoder.matches(
                user.getPassword(),
                existingUser.getPassword())) {

            return ResponseEntity.badRequest()
                    .body("Invalid email or password");
        }

        // Generate JWT
        String token = jwtService.generateToken(
                existingUser.getUserId(),
                existingUser.getEmail()
        );

        Map<String, Object> response = new HashMap<>();

        response.put("message", "Login successful");
        response.put("token", token);
        response.put("userId", existingUser.getUserId());
        response.put("name", existingUser.getName());
        response.put("email", existingUser.getEmail());

        return ResponseEntity.ok(response);
    }
    @GetMapping("/profile")
    public ResponseEntity<?> profile(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401)
                    .body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);

        try {
            String email = jwtService.extractEmail(token);

            User user = userRepository.findByEmail(email)
                    .orElse(null);

            if (user == null) {
                return ResponseEntity.status(401)
                        .body("User not found");
            }

            Map<String, Object> response = new HashMap<>();
            response.put("userId", user.getUserId());
            response.put("name", user.getName());
            response.put("email", user.getEmail());
            response.put("phone", user.getPhone());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(401)
                    .body("Invalid or expired token");
        }
    }




}
