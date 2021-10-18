package com.sample.app01.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sample.app01.model.Register;

import org.springframework.stereotype.Repository;
@Repository
public interface registerRepository extends JpaRepository<Register,Long> {
	
	public Optional <Register> findByEmail(String email);

	public boolean existsByEmail(String email);

	public boolean findByEmailAndPassword(String email, String password);

}
