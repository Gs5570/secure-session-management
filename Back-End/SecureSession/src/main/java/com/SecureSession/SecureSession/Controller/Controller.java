package com.SecureSession.SecureSession.Controller;

import com.SecureSession.SecureSession.Repo.UserRepo;
import com.SecureSession.SecureSession.Model.AuthenticationRequest;
import com.SecureSession.SecureSession.Model.AuthenticationResponse;
import com.SecureSession.SecureSession.Model.RegisterRequest;
import com.SecureSession.SecureSession.Model.User;
import com.SecureSession.SecureSession.Services.AuthenticationService;
import com.SecureSession.SecureSession.Services.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class Controller {
    private final UserRepo userRepo;
    private final AuthenticationService service;
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.registerAdmin(request));
    }
    @GetMapping("/admin/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepo.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest request) {
        log.debug("Login request received - Username: {}, Password: {}", request.getUsername(), request.getPassword());
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/user/welcome")
    public ResponseEntity<String> userWelcome() {
        return ResponseEntity.ok("Welcome to the User Dashboard!");
    }
    

}
