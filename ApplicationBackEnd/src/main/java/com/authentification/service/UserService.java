package com.authentification.service;

import com.authentification.entities.PasswordResetToken;
import com.authentification.entities.User;
import com.authentification.exceptions.InvalidTokenException;
import com.authentification.exceptions.UserNotFoundException;
import com.authentification.jwt.JwtUtils;
import com.authentification.jwt.Utils;
import com.authentification.payload.JwtResponse;
import com.authentification.payload.LoginRequest;
import com.authentification.payload.MessageResponse;
import com.authentification.payload.SignupRequest;
import com.authentification.repositories.PasswordResetTokenRepository;
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

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


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
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;


    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

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



    public Map<String, Object> registerUser(SignupRequest signUpRequest) throws IOException {
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
                "",
                signUpRequest.getHomeAddress(),
                signUpRequest.getAvgResponseTime(),
                signUpRequest.getPhone(),
                signUpRequest.getDescription(),
                encoder.encode(signUpRequest.getPassword()));
       /* if (signUpRequest.getProfilePicture() != null) {
            String fileName = signUpRequest.getProfilePicture().getOriginalFilename();
            Path path = Paths.get("C:/ProfilePictures/" + fileName) ;
            Files.write(path, signUpRequest.getProfilePicture().getBytes());
            user.setProfilePicturePath(path.toString());
        }*/
        userRepository.save(user);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        String successMessage = "User " + signUpRequest.getUsername() + " registered successfully!";
        String id="id"+ user.getId_user();
        Map<String, Object> newResponse = new HashMap<>();
        newResponse.put("message", successMessage);
        newResponse.put("token", jwt);
        newResponse.put("id", user.getId_user());
        return newResponse;
    }

    public void logoutUser(HttpServletRequest request) {
        String token = extractJwtFromRequest(request);
        jwtUtils.invalidateJwtToken(token);
    }
    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);
        if (resetToken == null || resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenException("Invalid or expired token");
        }

        User user = userRepository.findByEmail(resetToken.getUserEmail()).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
        passwordResetTokenRepository.delete(resetToken);
    }

    public String forgotPassword(String email) throws MessagingException, IOException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = userOptional.get();

        PasswordResetToken token = passwordResetTokenRepository.findByUserEmail(email);
        if (token != null) {
            passwordResetTokenRepository.delete(token);
        }

        String newToken = Utils.generateRandomToken();
        PasswordResetToken newResetToken = new PasswordResetToken();
        newResetToken.setToken(newToken);
        newResetToken.setUserEmail(email);
        newResetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); // Set token expiry to 1 hour
        passwordResetTokenRepository.save(newResetToken);

        String resetUrl = "https://myapp.com/resetpassword?token=" + newToken;

        emailService.sendPasswordResetEmail(user, newResetToken, resetUrl);

        return newToken;
    }


}






