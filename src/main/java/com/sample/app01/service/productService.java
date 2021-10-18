package com.sample.app01.service;

import java.util.List;

import com.sample.app01.model.Product;
public interface productService {

	Product addProduct(Product product);

	List<Product> getAllProduct();

}
