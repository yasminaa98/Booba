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
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Arrays;


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
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest , HttpSession session) {
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

        userRepository.save(user);
        session.setAttribute("id", user.getId_user());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        String successMessage = "User " + signUpRequest.getUsername() + " registered successfully!";
        String tokenMessage = "Signup token: " + jwt;
        return ResponseEntity.ok().body(Arrays.asList(new MessageResponse(successMessage), new MessageResponse(tokenMessage)));
    }

  }

