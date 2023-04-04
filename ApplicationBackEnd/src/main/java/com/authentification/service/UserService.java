package com.authentification.service;

import com.authentification.entities.User;
import com.authentification.jwt.JwtUtils;
import com.authentification.payload.JwtResponse;
import com.authentification.payload.LoginRequest;
import com.authentification.payload.MessageResponse;
import com.authentification.payload.SignupRequest;
import com.authentification.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
 @Transactional

public class UserService {

   /* @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                null);
    }


    public ResponseEntity<?> registerUser(SignupRequest signUpRequest, HttpSession session) {
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
        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPicture(),
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
        String message = "User" + signUpRequest.getUsername() + "registered successfully !  " +
                "signup token : " + jwt;
        return ResponseEntity.ok(new MessageResponse(message));
    }*/

  }





