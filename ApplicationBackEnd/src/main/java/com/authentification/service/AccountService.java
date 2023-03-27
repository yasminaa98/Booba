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
    public ResponseEntity<MessageResponse> updateAccount(Long id_user, User user) {
        User existentUser = userRepository.findById(id_user).orElse(null);

        if (existentUser == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Not found"));
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
            return ResponseEntity.ok(new MessageResponse("modified successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Not modified"));
        }


    }

    // Delete account:
    public ResponseEntity<MessageResponse> deleteAccount(Long userId) {
        User existentUser = userRepository.findById(userId).orElse(null);

        if (existentUser == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Not found"));
        }

        try {
            userRepository.deleteById(userId);
            return ResponseEntity.ok(new MessageResponse("Deleted successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Not delete"));
        }
    }

}

