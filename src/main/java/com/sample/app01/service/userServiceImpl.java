package com.sample.app01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sample.app01.model.User;
import com.sample.app01.repository.userRepository;

@Service
public class userServiceImpl implements userService {
	
	

	
	@Autowired
	private userRepository userRepo;
	
	// Get all user Details..
	@Override
	public List<User> getAllUser() {
		return userRepo.findAll();
	}
	
	@Override
	public User addUser(User user) {
		return userRepo.save(user);
	}
	
	@Override
	public User updateUser(User user,Long id) {
		User thisUser=userRepo.findById(id).orElseThrow();
		if(user.getImgByte() != null) {
			thisUser.setImgByte(user.getImgByte());}
		
		if(user.getUserName() != null) {
			thisUser.setUserName(user.getUserName());}
		
		userRepo.save(thisUser);
		return thisUser;
	}
	
	@Override
	public void deleteUser(long id) {
		java.util.Optional<User> optional=userRepo.findById(id);
		if(optional.isPresent()) {
			userRepo.deleteById(id);
		}
		else {
			throw new RuntimeException("User not found for Id : "+id);
		}
		
		
	}

}
