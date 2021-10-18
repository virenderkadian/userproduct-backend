package com.sample.app01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.app01.model.Product;

@Repository
public interface productRepository extends JpaRepository<Product,Long> {

}
