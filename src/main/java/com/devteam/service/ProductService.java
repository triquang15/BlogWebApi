package com.devteam.service;

import java.util.List;

import com.devteam.entity.Product;

public interface ProductService {
	List<Product> searchProducts(String query);

	Product createProduct(Product product);
}
