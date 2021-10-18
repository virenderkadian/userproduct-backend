package com.sample.app01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app01.model.User;
import com.sample.app01.service.userService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class userController {
	
	@Autowired 
	private userService uService;

	@GetMapping("/display")
	public List<User> listUsers(){
		System.out.println("display");
		return uService.getAllUser();
	}
	
	@PostMapping("/add")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		return new ResponseEntity<User>(uService.addUser(user),HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@PathVariable (value ="id") long id, @RequestBody User user){
		return new ResponseEntity<User>(uService.updateUser(user,id),HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable ( value="id") long id) {
		uService.deleteUser(id);
		return new ResponseEntity<String>("User Deleted",HttpStatus.OK);
	}
}
