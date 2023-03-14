package com.authentification.controllers;

import com.authentification.entities.User;
import com.authentification.payload.MessageResponse;
import com.authentification.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService ;

    // Update account :

    @PutMapping("/{user_id}/update-account")
    public ResponseEntity<?> updateAccount(@PathVariable("user_id") Long user_id , @RequestBody User user ) {

        ResponseEntity<MessageResponse> accountModified = accountService.updateAccount(user_id, user) ;

        if (accountModified == null) {
            return ResponseEntity.ok(new MessageResponse("Not modified !"));
        }
        return ResponseEntity.ok(new MessageResponse("Modified successfully !"));
    }


    // Delete account :

    @DeleteMapping("/{user_id}/delete-account")
    public ResponseEntity<?> deleteAccount(@PathVariable("user_id") Long user_id) {
        accountService.deleteAccount(user_id);
        return ResponseEntity.ok(new MessageResponse("Deleted successfully!"));
    }


}
