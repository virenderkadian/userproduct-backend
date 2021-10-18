package com.sample.app01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.app01.model.User;

@Repository
public interface userRepository  extends JpaRepository<User,Long> {

}
