package com.authentification.service;

import com.authentification.jwt.JwtUtils;
import com.authentification.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

   /* public ResponseEntity<?> authenticateUser(LoginRequest loginRequest, HttpSession session){

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
    }


    public ResponseEntity<?> registerUser (SignupRequest signUpRequest, HttpSession session) {
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
        // Create new user's account
        User user = new User(
                signUpRequest.getUsername(),
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
        // Generate JWT token
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        // Print token to console
        System.out.println("JWT token: " + jwt);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!")+jwt);

    }
*/

}
