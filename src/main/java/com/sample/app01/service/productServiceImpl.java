package com.sample.app01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app01.model.Product;
import com.sample.app01.repository.productRepository;

@Service
public class productServiceImpl  implements productService {

	@Autowired
	private productRepository productRepo;
	
	@Override
	public List<Product> getAllProduct() {
		return productRepo.findAll();
	}
	
	@Override
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}
	
}
