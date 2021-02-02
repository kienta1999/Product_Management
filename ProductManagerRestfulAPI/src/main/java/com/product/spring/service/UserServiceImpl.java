package com.product.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.spring.model.User;
import com.product.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User findUserByUsername(String username) {
		List<User> allUser = userRepository.findByUsername(username);
		return allUser.size() == 0 ? null : allUser.get(0);
	}

	@Override
	public User findUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public void registerUser(User user) {
		userRepository.save(user);
	}
	
	public boolean isValidUser(String username, String password) {
		User user = findUserByUsername(username);
		if(user != null && user.getPassword().equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	
}
