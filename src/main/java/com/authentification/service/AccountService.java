package com.authentification.service;

import com.authentification.entities.FileData;
import com.authentification.entities.User;
import com.authentification.jwt.JwtUtils;
import com.authentification.payload.MessageResponse;
import com.authentification.repositories.FileDataRepository;
import com.authentification.repositories.UserRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils ;
    @Autowired
    private FileDataRepository fileDataRepository;
    private final String FOLDER_PATH="C:/Users/Asus/IdeaProjects/booba/ApplicationBackEnd/src/main/resources/images/profiles/";

    /***
     * Api for getting a user object by username
     * @param username
     * @return
     */
    public ResponseEntity<Optional<User>> getUserByUsername (String username){
        Optional<User> user = userRepository.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<Optional<User>> getUserById (Long id_user){
        Optional<User> user = userRepository.findById(id_user);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /***
     * Api for updating firstname
     * @param id_user
     * @param newFirstName
     * @return
     */
    public ResponseEntity<MessageResponse> updateFirstName(Long id_user, String newFirstName) {
        User existentUser = userRepository.findById(id_user).orElse(null);
        if (existentUser == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        existentUser.setFirstname(newFirstName);
        try {
            userRepository.save(existentUser);
            return ResponseEntity.ok(new MessageResponse("Lastname modified successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to modify lastname"));
        }
    }
  /*  public ResponseEntity<MessageResponse> updatePicture(Long id_user, String newPicture) {
        User existentUser = userRepository.findById(id_user).orElse(null);
        if (existentUser == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        existentUser.setProfilePicturePath(newPicture);
        try {
            userRepository.save(existentUser);
            return ResponseEntity.ok(new MessageResponse("picture modified successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to modify picture"));
        }
    } */


    /***
     * Api for updating lastname
     * @param id_user
     * @param newLastName
     * @return
     */
    public ResponseEntity<MessageResponse> updateLastName(Long id_user, String newLastName) {
        User existentUser = userRepository.findById(id_user).orElse(null);
        if (existentUser == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        existentUser.setLastname(newLastName);
        try {
            userRepository.save(existentUser);
            return ResponseEntity.ok(new MessageResponse("Lastname modified successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to modify lastname"));
        }
    }

    /***
     * Api for updating username
     * @param id_user
     * @param newUsername
     * @return
     */
    public ResponseEntity<MessageResponse> updateUsername(Long id_user, String newUsername) {
        User existentUser = userRepository.findById(id_user).orElse(null);
        if (existentUser == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        Optional<User> userWithNewUsername = userRepository.findByUsername(newUsername);
        if (userWithNewUsername.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Username already exists"));
        }
        existentUser.setUsername(newUsername);
        try {
            userRepository.save(existentUser);
            return ResponseEntity.ok(new MessageResponse("Username modified successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to modify username"));
        }
    }


    /***
     * Api for updating email
     * @param id_user
     * @param newEmail
     * @return
     */
    public ResponseEntity<MessageResponse> updateEmail(Long id_user, String newEmail) {
        User existentUser = userRepository.findById(id_user).orElse(null);
        if (existentUser == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        Optional<User> userWithNewEmail = userRepository.findByEmail(newEmail);
        if (userWithNewEmail.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email already exists"));
        }
        existentUser.setEmail(newEmail);
        try {
            userRepository.save(existentUser);
            return ResponseEntity.ok(new MessageResponse("Email modified successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to modify email"));
        }
    }


    /***
     * Api for updating password
     * @param id_user
     * @param newPassword
     * @return
     */
    public ResponseEntity<MessageResponse> updatePassword(Long id_user, String newPassword) {
        User existentUser = userRepository.findById(id_user).orElse(null);
        if (existentUser == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        existentUser.setPassword(newPassword);
        try {
            userRepository.save(existentUser);
            return ResponseEntity.ok(new MessageResponse("Password modified successfully!"));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to modify password"));
        }
    }

    /***
     * Api for updating homeAddress
     * @param id_user
     * @param newHomeAddress
     * @return
     */
    public ResponseEntity<MessageResponse> updateHomeAddress(Long id_user, String newHomeAddress) {
        User existentUser = userRepository.findById(id_user).orElse(null);
        if (existentUser == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        existentUser.setHomeAddress(newHomeAddress);
        try {
            userRepository.save(existentUser);
            return ResponseEntity.ok(new MessageResponse("Home address modified successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to modify home address"));
        }
    }

    /***
     * Api for updating phone
     * @param id_user
     * @param newPhone
     * @return
     */
    public ResponseEntity<MessageResponse> updatePhone(Long id_user, int newPhone) {
        User existentUser = userRepository.findById(id_user).orElse(null);
        if (existentUser == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        existentUser.setPhone(newPhone);
        try {
            userRepository.save(existentUser);
            return ResponseEntity.ok(new MessageResponse("Phone number modified successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to modify phone number"));
        }
    }

    /***
     * Api for updating avgResponseTime
     * @param id_user
     * @param newAvgResponseTime
     * @return
     */
    public ResponseEntity<MessageResponse> updateAvgResponseTime(Long id_user, String newAvgResponseTime) {
        User existentUser = userRepository.findById(id_user).orElse(null);
        if (existentUser == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        existentUser.setAvgResponseTime(newAvgResponseTime);
        try {
            userRepository.save(existentUser);
            return ResponseEntity.ok(new MessageResponse("Average response time modified successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to modify average response time"));
        }
    }

    /***
     * Api for updating description
     * @param id_user
     * @param newDesciption
     * @return
     */
    public ResponseEntity<MessageResponse> updateDescription(Long id_user,String newDesciption) {
        User existentUser = userRepository.findById(id_user).orElse(null) ;
        if (existentUser == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found")) ;
        }
        existentUser.setDescription(newDesciption);
        try {
            userRepository.save(existentUser);
            return ResponseEntity.ok(new MessageResponse("Description modified successfully!")) ;
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to modify description"));
        }
    }


    /***
     * Api for deleting a user account
     * @param id_user
     * @return
     */
    public ResponseEntity<MessageResponse> deleteAccount(Long id_user) {
        User existentUser = userRepository.findById(id_user).orElse(null);

        if (existentUser == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Not found"));
        }

        try {
            userRepository.deleteById(id_user);
            return ResponseEntity.ok(new MessageResponse("Deleted successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Not deleted"));
        }
    }
    public ResponseEntity<Map<String, Object>> updateProfilePicture(String token,MultipartFile profilePicture) throws IOException, IOException {
        Long id_user = jwtUtils.getUserIdFromToken(token);
        Map<String, Object> response = new HashMap<>();
        Optional<User> userOptional = userRepository.findById(id_user);
        if (!userOptional.isPresent()) {
            response.put("message", "Error: User not found!");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        String filePath=FOLDER_PATH+profilePicture.getOriginalFilename();
        FileData fileData=fileDataRepository.save(FileData.builder()
                .name(profilePicture.getOriginalFilename())
                .type(profilePicture.getContentType())
                .filePath(filePath).build());
        profilePicture.transferTo(new File(filePath));
        user.setProfilePicture(fileData.getName());
        userRepository.save(user);

       /* User user = userOptional.get();
        String originalFilename = profilePicture.getOriginalFilename();
        String fileName = originalFilename.split("\\.", 2)[0];
        String fileExtension = originalFilename.split("\\.", 2)[1];
        String modifiedDate = new Date().toString().replace(':', '.');
        byte[] bytes = profilePicture.getBytes();
        Path path = Paths.get("C:/Users/Asus/IdeaProjects/booba/ApplicationBackEnd/src/main/resources/images/profiles/" + fileName + modifiedDate + "." + fileExtension);
        Files.write(path, bytes);
        user.setProfilePicture(fileName + modifiedDate + "." + fileExtension);
        userRepository.save(user);*/
        response.put("message", "Profile picture updated successfully!");
        response.put("profilePicture", user.getProfilePicture());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

