package com.authentification.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import com.authentification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.authentification.payload.LoginRequest;
import com.authentification.payload.SignupRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
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
	 * @param
	 * @param
	 * @return
	 */

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@ModelAttribute SignupRequest signUpRequest, HttpSession session) throws IOException {
		Map<String, Object> response = userService.registerUser(signUpRequest, session);
		if (response.containsKey("id")) {
			return ResponseEntity.ok().body(response);
		} else {
			return ResponseEntity.badRequest().body(response);
		}
	}

	/*@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest, HttpSession session) throws IOException {
		Map<String, Object> response = userService.registerUser(signUpRequest, session);
		return ResponseEntity.status((int) response.get("status")).body(response.get("body"));
	}*/

	/*@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestPart("profilePicture") MultipartFile profilePicture,
										  @Valid @RequestBody SignupRequest signUpRequest,
										  HttpSession session) throws IOException
	{return userService.registerUser(signUpRequest, session);}*/



}



