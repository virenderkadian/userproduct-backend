package com.sample.app01.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sample.app01.model.CustomAdmin;
import com.sample.app01.model.Register;
import com.sample.app01.repository.registerRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	  registerRepository rRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Register register = rRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with "
                + "user name "+ email + " not found"));
		System.out.println("user details-----------"+register);
		return CustomAdmin.createInstance(register);
		//return new CustomAdmin(2,"virender","virender@mail.com","$2a$10$rEG9n4wJPYPfYhDSJ6UYiOC3dTPXTFdvSGB2Hu5UJw1ns2SWiBxFC");
	}

}
