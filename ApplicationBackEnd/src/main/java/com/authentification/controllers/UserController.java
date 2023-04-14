package com.authentification.controllers;


import com.authentification.payload.MessageResponse;
import com.authentification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.authentification.payload.LoginRequest;
import com.authentification.payload.SignupRequest;


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



}



