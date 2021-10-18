package com.sample.app01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app01.model.Register;
import com.sample.app01.repository.registerRepository;


@Service
public class registerService implements registerServiceimpl {

	@Override
	public Register saveAdmin(Register register) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Register getAdminUserName(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Register getLoginCred(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}
