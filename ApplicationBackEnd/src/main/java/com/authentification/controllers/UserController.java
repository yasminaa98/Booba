package com.authentification.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import com.authentification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.authentification.payload.LoginRequest;
import com.authentification.payload.SignupRequest;

import java.util.Arrays;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Autowired
	private UserService userService;

	/***
	 * Api for signing in
	 * @param loginRequest
	 * @param session
	 * @return
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) {
		return userService.authenticateUser(loginRequest, session);
	}

	/***
	 * Api for signing up
	 * @param signUpRequest
	 * @param session
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, HttpSession session) {
		return userService.registerUser(signUpRequest, session);
	}
}



