package com.authentification.service;

import com.authentification.entities.User;
import com.authentification.jwt.JwtUtils;
import com.authentification.payload.JwtResponse;
import com.authentification.payload.LoginRequest;
import com.authentification.payload.MessageResponse;
import com.authentification.payload.SignupRequest;
import com.authentification.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Service
@Transactional

public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;


    /***
     * Api for signing in
     * @param loginRequest
     * @param session
     * @return
     */
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest, HttpSession session) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            session.setAttribute("id", userDetails.getId());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    null));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Invalid username or password"));
        }

    }

    /***
     * Api for signing up
     * @param signUpRequest
     * @param session
     * @return
     */

    public Map<String, Object> registerUser(SignupRequest signUpRequest , HttpSession session) throws IOException {
        Map<String, Object> response = new HashMap<>();
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            response.put("message", "Error: Username is already taken!");
            return response;
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            response.put("message", "Error: Email is already in use!");
            return response;
        }

        User user = new User( signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getFirstname(),
                signUpRequest.getLastname(),
                signUpRequest.getHomeAddress(),
                signUpRequest.getAvgResponseTime(),
                signUpRequest.getPhone(),
                signUpRequest.getDescription(),
                encoder.encode(signUpRequest.getPassword()));

        if (signUpRequest.getProfilePicture() != null) {
            String fileName = signUpRequest.getProfilePicture().getOriginalFilename();
            Path path = Paths.get("C:/ProfilePictures/" + fileName) ;
            Files.write(path, signUpRequest.getProfilePicture().getBytes());
            user.setProfilePicturePath(path.toString());
        }

        userRepository.save(user);
        session.setAttribute("id", user.getId_user());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        String successMessage = "User " + signUpRequest.getUsername() + " registered successfully!";
        String tokenMessage = "Signup token: " + jwt;
        response.put("message", Arrays.asList(new MessageResponse(successMessage), new MessageResponse(tokenMessage)));
        response.put("id", user.getId_user());
        if (response.containsKey("id")) {
            return ResponseEntity.ok().body(response).getBody();
        } else {
            return ResponseEntity.badRequest().body(response).getBody();
        }
    }


     /*public ResponseEntity<?> registerUser(SignupRequest signUpRequest , HttpSession session) throws IOException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User( signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getFirstname(),
                signUpRequest.getLastname(),
                signUpRequest.getHomeAddress(),
                signUpRequest.getAvgResponseTime(),
                signUpRequest.getPhone(),
                signUpRequest.getDescription(),
                encoder.encode(signUpRequest.getPassword()));
        if (signUpRequest.getProfilePicture() != null) {
            String fileName = signUpRequest.getProfilePicture().getOriginalFilename();
            Path path = Paths.get("C:/ProfilePictures/" + fileName) ;
            Files.write(path, signUpRequest.getProfilePicture().getBytes());
            user.setProfilePicturePath(path.toString());
        }

        userRepository.save(user);
        session.setAttribute("id", user.getId_user());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        String successMessage = "User " + signUpRequest.getUsername() + " registered successfully!";
        String tokenMessage = "Signup token: " + jwt;
        return ResponseEntity.ok().body(Arrays.asList(new MessageResponse(successMessage), new MessageResponse(tokenMessage)));
    }*/

    /* public ResponseEntity<?> registerUser(SignupRequest signUpRequest , HttpSession session) throws IOException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User( signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getFirstname(),
                signUpRequest.getLastname(),
                signUpRequest.getHomeAddress(),
                signUpRequest.getAvgResponseTime(),
                signUpRequest.getPhone(),
                signUpRequest.getDescription(),
                encoder.encode(signUpRequest.getPassword()));
          if (signUpRequest.getProfilePicture() != null) {
            String fileName = signUpRequest.getProfilePicture().getOriginalFilename();
            Path path = Paths.get("C:/ProfilePictures/" + fileName) ;
            Files.write(path, signUpRequest.getProfilePicture().getBytes());
            user.setProfilePicturePath(path.toString());
          }

        userRepository.save(user);
        session.setAttribute("id", user.getId_user());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        String successMessage = "User " + signUpRequest.getUsername() + " registered successfully! ID: " + user.getId_user();
        String tokenMessage = "Signup token: " + jwt;
        return ResponseEntity.ok().body(Arrays.asList(new MessageResponse(successMessage), new MessageResponse(tokenMessage)));
    }*/


}

