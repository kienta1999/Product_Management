package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Product;

public interface ProductService {
	Iterable<Product> findAll();

    List<Product> search(String q);

    Product findOne(int id);

    void save(Product product);

    void delete(Product product);
}
