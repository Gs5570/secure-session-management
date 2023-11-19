package com.SecureSession.Controller;

import com.SecureSession.Model.Train;
import com.SecureSession.Model.User;
import com.SecureSession.Repo.TrainRepo;
import com.SecureSession.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/")
public class Controller {
    private final UserRepo userRepo;
    private final TrainRepo trainRepo;

    @Autowired
    public Controller(UserRepo userRepo, TrainRepo trainRepo) {
        this.userRepo = userRepo;
        this.trainRepo = trainRepo;
    }

    @GetMapping("/trains")
    public ResponseEntity<List<Train>> getTrains() {
        List<Train> trains = trainRepo.findAll();
        return ResponseEntity.ok(trains);
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        // Check if the user already exists
        if (userRepo.existsById(newUser.getUserId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }

        newUser.setIsApproved(false); // Set approval status to false
        userRepo.save(newUser);
        return ResponseEntity.ok("User registered successfully, pending admin approval");
    }

    

}
