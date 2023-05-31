package com.authentification.controllers;

import com.authentification.entities.User;
import com.authentification.payload.MessageResponse;
import com.authentification.repositories.FileDataRepository;
import com.authentification.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/accounts")
public class AccountController {


    @Autowired
    AccountService accountService ;

    /***
     * Api for getting user object by username
     * @param username
     * @return
     */
    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable("username") String username) {
        return accountService.getUserByUsername(username);
    }
    @GetMapping("/getUserById/{id_user}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id_user") Long id_user) {
        return accountService.getUserById(id_user);
    }

    /***
     * Api for updating firstname
     * @param id_user
     * @param newFirstName
     * @return
     */
    @PutMapping("/{id_user}/update-firstname")
    public ResponseEntity<MessageResponse> updateFirstName(@PathVariable("id_user") Long id_user,
                                                           @RequestParam("newFirstName") String newFirstName) {
        return accountService.updateFirstName(id_user, newFirstName);
    }
 /*   @PutMapping("/{id_user}/update-picture")
    public ResponseEntity<MessageResponse> updatePicture(@PathVariable("id_user") Long id_user,
                                                           @RequestParam("newPicture") String newPicture) {
        return accountService.updatePicture(id_user, newPicture);
    }*/
    @PutMapping("/update-profile-picture")
    public ResponseEntity<?> updateProfilePicture(@RequestHeader("Authorization") String token, @RequestParam("profilePicture") MultipartFile profilePicture) throws IOException, IOException {

        return accountService.updateProfilePicture(token,profilePicture);
    }

    /***
     * Api for updating lastname
     * @param id_user
     * @param newLastName
     * @return
     */
    @PutMapping("/{id_user}/update-lastname")
    public ResponseEntity<MessageResponse> updateLastName(@PathVariable("id_user") Long id_user,
                                                          @RequestParam("newLastName") String newLastName) {
        return accountService.updateLastName(id_user, newLastName);
    }

    /***
     * Api for updating username
     * @param id_user
     * @param newUsername
     * @return
     */
    @PutMapping("/{id_user}/update-username")
    public ResponseEntity<MessageResponse> updateUsername(@PathVariable("id_user") Long id_user,
                                                          @RequestParam("newUsername") String newUsername) {
        return accountService.updateUsername(id_user, newUsername);
    }

    /***
     * Api for updating email
     * @param id_user
     * @param newEmail
     * @return
     */
    @PutMapping("/{id_user}/update-email")
    public ResponseEntity<MessageResponse> updateEmail(@PathVariable("id_user") Long id_user,
                                                       @RequestParam("newEmail") String newEmail) {
        return accountService.updateEmail(id_user, newEmail);
    }

    /***
     * Api for updating password
     * @param id_user
     * @param newPassword
     * @return
     */
    @PutMapping("/{id_user}/update-password")
    public ResponseEntity<MessageResponse>updatePassword(@PathVariable("id_user") Long id_user,
                                                         @RequestParam("newPassword") String newPassword) {
        return accountService.updatePassword(id_user,newPassword);
    }

    /***
     * Api for updating homeAddress
     * @param id_user
     * @param newHomeAddress
     * @return
     */
    @PutMapping("/{id_user}/update-homeaddress")
    public ResponseEntity<MessageResponse> updateHomeAddress(@PathVariable("id_user") Long id_user,
                                                             @RequestParam("newHomeAddress") String newHomeAddress) {
        return accountService.updateHomeAddress(id_user, newHomeAddress);
    }

    /***
     * Api for updating phone
     * @param id_user
     * @param newPhone
     * @return
     */
    @PutMapping("/{id_user}/update-phone")
    public ResponseEntity<MessageResponse> updatePhone(@PathVariable("id_user") Long id_user,
                                                       @RequestParam("newPhone") int newPhone) {
        return accountService.updatePhone(id_user, newPhone);
    }

    /***
     * Api for updating avgResponseTime
     * @param id_user
     * @param newAvgResponseTime
     * @return
     */
    @PutMapping("/{id_user}/update-avgresponsetime")
    public ResponseEntity<MessageResponse> updateAvgResponseTime(@PathVariable("id_user") Long id_user,
                                                                 @RequestParam("newAvgResponseTime") String newAvgResponseTime) {
        return accountService.updateAvgResponseTime(id_user, newAvgResponseTime);
    }

    /***
     * Api for updating description
     * @param id_user
     * @param newDescription
     * @return
     */
    @PutMapping("/{id_user}/update-description")
    public ResponseEntity<MessageResponse> updateDescription(@PathVariable("id_user") Long id_user,
                                                                 @RequestParam("description") String newDescription) {
        return accountService.updateDescription(id_user, newDescription);
    }

    /***
     * Api for deleting a user account
     * @param id_user
     * @return
     */
    @DeleteMapping("/{id_user}/delete-account")
    public ResponseEntity<?> deleteAccount(@PathVariable("id_user") Long id_user) {
        return accountService.deleteAccount(id_user);
    }
}
