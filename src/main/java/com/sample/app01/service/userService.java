package com.sample.app01.service;

import java.util.List;



import com.sample.app01.model.User;



public interface userService {

	List<User> getAllUser();

	User addUser(User user);

	User updateUser(User user, Long id);

	void deleteUser(long id);





}
