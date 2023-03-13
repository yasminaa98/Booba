package com.authentification.service;

import com.authentification.entities.User;
import com.authentification.payload.MessageResponse;
import com.authentification.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    // Update account:
    public ResponseEntity<MessageResponse> updateAccount(Long userId, User user) {
        User existentUser = userRepository.findById(userId).orElse(null);

        if (existentUser == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }

        existentUser.setFirstname(user.getFirstname());
        existentUser.setLastname(user.getLastname());
        existentUser.setUsername(user.getUsername());
        existentUser.setEmail(user.getEmail());
        existentUser.setHomeAddress(user.getHomeAddress());
        existentUser.setPhone(user.getPhone());
        existentUser.setAvgResponseTime(user.getAvgResponseTime());
        existentUser.setDescription(user.getDescription());

        try {
            userRepository.save(existentUser);
            return ResponseEntity.ok(new MessageResponse("User modified successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to update user"));
        }
    }

    // Delete account:
    public ResponseEntity<MessageResponse> deleteAccount(Long userId) {
        User existentUser = userRepository.findById(userId).orElse(null);

        if (existentUser == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }

        try {
            userRepository.deleteById(userId);
            return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to delete user"));
        }
    }
}

