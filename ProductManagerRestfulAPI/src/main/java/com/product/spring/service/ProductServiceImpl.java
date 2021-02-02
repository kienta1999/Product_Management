package com.product.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.spring.model.Product;
import com.product.spring.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> findAllProduct() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}
	
	

	@Override
	public List<Product> findAllProductOf(int userid) {
		// TODO Auto-generated method stub
		return productRepository.findByUserid(userid);
	}

	@Override
	public Product findProductById(int id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public Product deleteProductById(int id) {
		Product product = findProductById(id);
		productRepository.deleteById(id);
		return product;
	}

	@Override
	public Product updateProductById(int id, String name, int price) {
		Product product = findProductById(id);
		product.setName(name);
		product.setPrice(price);
		productRepository.save(product);
		return product;
	}
	
	@Override
	public void addProduct(Product product) {
		productRepository.save(product);
	}

}
