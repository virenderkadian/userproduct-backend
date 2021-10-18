package com.sample.app01.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import com.sample.app01.config.JwtTokenUtil;
import com.sample.app01.model.AuthResponse;
import com.sample.app01.model.Register;
import com.sample.app01.repository.registerRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin")
@RestController
public class registerController {
	
	@Autowired
	  registerRepository rRepository;
	
	@Autowired
	  JwtTokenUtil jwtTokenUtil;
	@Autowired
	  AuthenticationManager authManager;
	 @Autowired
	  PasswordEncoder encoder;
	 
	 @PostMapping("/signup")
	  public ResponseEntity<?> userSignup(@RequestBody Register register) {
		 String tempEmail=register.getEmail();
		 System.out.println(tempEmail);
	    if(rRepository.existsByEmail(register.getEmail())){
	      return ResponseEntity.badRequest().body("Email is already taken");
	    }
	    Register user = new Register();
	    user.setAdminName(register.getAdminName());
	    user.setEmail(register.getEmail());
	    user.setPassword(encoder.encode(register.getPassword()));
	    //System.out.println("Encoded password--- " + user.getPassword());
	    rRepository.save(user);
	    return ResponseEntity.ok("User signed up successfully");
	  }
	
	
	@PostMapping("/login")
	  public ResponseEntity<?> userLogin(@Valid @RequestBody Register register) {
		System.out.println("Hello 1 " + register.getEmail());
	    //System.out.println("AuthController -- userLogin");
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(register.getEmail(), register.getPassword()));
	    System.out.println(authentication);
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String token=null;
	    if(authentication==null) {
	    	System.out.println("I am null");
	    }
	    token = jwtTokenUtil.generateJwtToken(authentication);
	    System.out.println(token);
	   // CustomAdmin userBean = (CustomAdmin) authentication.getPrincipal();
	    AuthResponse authResponse = new AuthResponse();
	    authResponse.setToken(token);
	    return ResponseEntity.ok(authResponse);
	  }

}
