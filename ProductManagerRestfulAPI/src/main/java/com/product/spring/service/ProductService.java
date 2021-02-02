package com.product.spring.service;

import java.util.List;

import com.product.spring.model.Product;

public interface ProductService {
	public List<Product> findAllProduct();
	public List<Product> findAllProductOf(int userid);
	public Product findProductById(int id);
	public Product deleteProductById(int id);
	public Product updateProductById(int id, String name, int price);
	public void addProduct(Product product);
}
