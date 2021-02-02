package com.product.spring.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.spring.model.Product;
import com.product.spring.model.Response;
import com.product.spring.model.User;
import com.product.spring.service.ProductService;
import com.product.spring.service.UserService;

@RestController
public class Controller {
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	Response response;
	
	Logger logger = LogManager.getLogger(Controller.class);
	
	/************************************User**************************************************/
	@GetMapping("/user")
	public List<User> home() {
		return userService.findAllUser();
	}
	@PostMapping("/user/register")
	public Response register(@ModelAttribute @RequestBody User user) {
//		logger.info(user);
		User dbUser = userService.findUserByUsername(user.getUsername());
		if(dbUser == null) {
			response.setBody(user);
			userService.registerUser(user);
			response.setStatus(201);
			response.setDescription("Registered");
		}
		else {
			response.setBody(dbUser);
			response.setStatus(400);
			response.setDescription("Bad Request");
		}
		response.setType("user");
		return response;
	}
	@PostMapping("/user/login")
	public Response login(@ModelAttribute @RequestBody User user) {
		User dbUser = userService.findUserByUsername(user.getUsername());
		response.setType("user");
		if(dbUser == null) {
			response.setStatus(400);
			response.setDescription("Wrong Username");
		}
		else if(!dbUser.getPassword().equals(user.getPassword())) {
			response.setStatus(400);
			response.setDescription("Wrong Password");
		}
		else {
			response.setStatus(202);
			response.setDescription("Login");
			response.setBody(dbUser);
		}
		return response;
	}
	
	/************************************Product**************************************************/
	/************************************READ**************************************************/
	@GetMapping("/product")
	public Response getAllProduct() {
		response.setBody(productService.findAllProduct());
		response.setStatus(202);
		response.setDescription("Accepted");
		response.setType("products");
		return response;
	}
	
	@GetMapping("/product/user/{userid}")
	public Response getProductOfUser(@PathVariable("userid") int userid) {
		response.setBody(productService.findAllProductOf(userid));
		response.setStatus(202);
		response.setDescription("Read");
		response.setType("products");
		return response;
	}
	@GetMapping("/product/{productid}")
	public Response getProduct(@PathVariable("productid") int productid) {
		Product product = productService.findProductById(productid);
		response.setBody(product);
		response.setType("product");
		if(product == null) {
			response.setStatus(400);
			response.setDescription("productid not exist");
		}
		else {
			response.setStatus(202);
			response.setDescription("Read");
		}
		return response;
	}
	
	/************************************CREATE**************************************************/
	@PostMapping("/product/create")
	public Response createProduct(@ModelAttribute Product product) {
		if(!isValidUser(product.getUserid())) {
			response.setBody(null);
			response.setDescription("User id not exist");
			response.setStatus(400);
		}
		else {
			productService.addProduct(product);
			response.setBody(product);
			response.setStatus(202);
			response.setDescription("Created");
		}
		response.setType("product");
		return response;
	}
	/************************************Update**************************************************/
	@PutMapping("/product/{productid}/update")
	public Response updateProduct(@PathVariable("productid") int productid, @ModelAttribute Product product) {
		Product productDb = productService.findProductById(productid);
		if(productDb == null) {
			if(isValidUser(product.getUserid())) {
				productService.addProduct(product);
				response.setBody(product);
				response.setStatus(201);
				response.setDescription("Created");
			}
			else {
				response.setBody(null);
				response.setDescription("User id not exist");
				response.setStatus(400);
			}
		}
		else {
			productService.updateProductById(product.getProductid(), product.getName(), product.getPrice());
			response.setBody(productService.findProductById(product.getProductid()));
			
			response.setStatus(202);
			response.setDescription("Updated");
			response.setBody(product);
		}
		response.setType("product");
		return response;
		
	}
	
	/************************************Delete**************************************************/
	@DeleteMapping("/product/{productid}/delete")
	public Response deleteProduct(@PathVariable("productid") int productid) {
		Product productDb = productService.findProductById(productid);
		if(productDb == null) {
			response.setBody(null);
			response.setDescription("Delete Fail. Product not exist.");
			response.setStatus(400);
		}
		else {
			response.setStatus(202);
			response.setDescription("Deleted");
			response.setBody(productDb);
			productService.deleteProductById(productid);
		}
		response.setType("product");
		return response;
	}
	
	public boolean isValidUser(int userid) {
		if(userService.findUserById(userid) == null) {
			return false;
		}
		return true;
	}
} 
