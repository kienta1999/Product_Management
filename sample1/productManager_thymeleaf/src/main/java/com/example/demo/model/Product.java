package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.ProductManagerApplication;

@Entity
@Table(name = "product")
public class Product implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "productid", nullable = false)
	private int productId;
	
	@Column(name = "userid", nullable = false)
	private int userId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "price", nullable = false)
	private int price;

	public Product() {
		super();
		userId = ProductManagerApplication.userid == null ? -1 : ProductManagerApplication.userid.intValue();
	}
	
	public Product(int productId, String name, int price) {
		this();
		this.productId = productId;
		this.name = name;
		this.price = price;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
