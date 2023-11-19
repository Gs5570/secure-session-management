package com.SecureSession.Repo;

import com.SecureSession.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByName(String name);
}
