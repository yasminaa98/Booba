package com.authentification.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.authentification.entities.User;
import com.authentification.jwt.JwtUtils;
import com.authentification.repositories.UserRepository;
import com.authentification.service.UserDetailsImpl;
import com.authentification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.GetMapping ;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.authentification.payload.JwtResponse;
import com.authentification.payload.LoginRequest;
import com.authentification.payload.MessageResponse;
import com.authentification.payload.SignupRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Autowired
	private UserService userService;


	/***
	 * Api for Signing in
	 * @param loginRequest
	 * @param session
	 * @return
	 */
	/*@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) {
		JwtResponse jwtResponse = userService.authenticateUser(loginRequest);
		session.setAttribute("id", jwtResponse.getId());
		return ResponseEntity.ok(jwtResponse);
	}*/

	/***
	 * Api for signing up
	 * @param signUpRequest
	 * @param session
	 * @return
	 */
	/*@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest, HttpSession session) {
		return userService.registerUser(signUpRequest, session);
	}

	@PostMapping("/logout")
	public ResponseEntity<MessageResponse> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok(new MessageResponse("User logged out successfully!"));
	}*/







	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) {

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

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
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

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}






































