package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.ProductManagerApplication;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Iterable<Product> findAll() {
		// TODO Auto-generated method stub
		Iterable<Product> allProduct = productRepository.findAll();
		List<Product> productWithId = new ArrayList<>();
		for(Product product: allProduct) {
			if(product.getUserId() == ProductManagerApplication.userid) {
				productWithId.add(product);
			}
		}
		return productWithId;
	}

	@Override
	public List<Product> search(String q) {
		// TODO Auto-generated method stub
		return productRepository.findByNameContaining(q);
	}

	@Override
	public Product findOne(int id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).get();
	}

	@Override
	public void save(Product product) {
		// TODO Auto-generated method stub
		productRepository.save(product);
	}

	@Override
	public void delete(Product product) {
		// TODO Auto-generated method stub
		productRepository.delete(product);
	}
	
}
