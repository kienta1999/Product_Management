package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {
	Iterable<User> findAll();

    User findOne(int id);

    void save(User user);

    void delete(User user);
}
