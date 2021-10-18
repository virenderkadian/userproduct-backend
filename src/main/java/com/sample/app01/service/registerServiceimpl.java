package com.sample.app01.service;

import com.sample.app01.model.Register;

public interface registerServiceimpl {

	Register saveAdmin(Register register);

	Register getAdminUserName(String email);

	Register getLoginCred(String userName, String password);
	
}
