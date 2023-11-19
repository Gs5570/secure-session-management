package com.SecureSession.Controller;

import com.SecureSession.Model.*;
import com.SecureSession.Repo.UserRepo;
import com.SecureSession.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@CrossOrigin("http://localhost:5173/")
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class Controller {
    private final UserRepo userRepo;
    private final AuthenticationService service;





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
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/user/welcome")
    public ResponseEntity<String> userWelcome() {
        return ResponseEntity.ok("Welcome to the User Dashboard!");
    }
    

}
