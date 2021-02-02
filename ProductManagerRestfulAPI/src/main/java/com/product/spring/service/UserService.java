package com.product.spring.service;

import java.util.List;

import com.product.spring.model.User;

public interface UserService {
	
	public User findUserByUsername(String username);
	public User findUserById(int id);
	public void registerUser(User user);
	public List<User> findAllUser();
	
}
