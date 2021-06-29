package com.example.demo.web;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.LoginException;
import com.example.demo.services.LoginService;
import com.example.demo.services.UserFacade;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
@RequestMapping("login")
public class LoginController {
	
	private LoginService service;

	@Autowired
	private Map<String, Session> sessions;
	
	public LoginController(LoginService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("{email}/{password}")
	public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) {
		try {
			// user authentication and appropriate Business Logic delivery
			UserFacade facade = service.Login(email, password);
			String token = UUID.randomUUID().toString();
			// couples unique token with the right facade and saves them into a global map. this is called a session.
			sessions.put(token, new Session(facade, System.currentTimeMillis()));
			// returns the unique token to user. In the client side this token will be saved in the sessionStorage using ts.
			return ResponseEntity.ok(token);
		} catch (LoginException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}

	// receives the unique user token and deletes the session.
	@GetMapping("logout/{token}")
	public ResponseEntity<?> logout(@PathVariable String token) {
		sessions.remove(token);
		return ResponseEntity.ok("logged out !");
	}
}
