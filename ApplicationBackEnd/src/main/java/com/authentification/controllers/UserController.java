package com.authentification.controllers;


import com.authentification.entities.ForgotPasswordResponse;
import com.authentification.exceptions.InvalidTokenException;
import com.authentification.exceptions.UserNotFoundException;
import com.authentification.payload.MessageResponse;
import com.authentification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.authentification.payload.LoginRequest;
import com.authentification.payload.SignupRequest;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Autowired
	private UserService userService;

		@PostMapping("/signin")
		public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
			return userService.authenticateUser(loginRequest);
		}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) throws IOException {
		Map<String, Object> response = userService.registerUser(signUpRequest);
		return ResponseEntity.ok().body(response);
	    }

	@PostMapping("/logout")
	public ResponseEntity<?> logoutUser(HttpServletRequest request) {
		userService.logoutUser(request);
		return ResponseEntity.ok().body(new MessageResponse("User successfully logged out"));
	}
	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestParam String email) throws MessagingException {
		try {
			String token = userService.forgotPassword(email);
			String emailMessage = "An email with instructions to reset your password has been sent to your email address.";
			String tokenMessage = token;
			return ResponseEntity.ok().body(new ForgotPasswordResponse(emailMessage,tokenMessage));
		} catch (UserNotFoundException ex) {
			return ResponseEntity.badRequest().body("User not found.");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
		try {
			userService.resetPassword(token, newPassword);
			return ResponseEntity.ok("Your password has been reset successfully.");
		} catch (InvalidTokenException ex) {
			return ResponseEntity.badRequest().body("Invalid token.");
		}
	}



}



