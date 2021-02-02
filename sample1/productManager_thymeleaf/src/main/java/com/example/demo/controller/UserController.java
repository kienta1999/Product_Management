package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.ProductManagerApplication;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("logout")
	public String logout(){
		//end session
		ProductManagerApplication.userid = null;
		return "redirect:/login";
	}
	
	@GetMapping("login")
	public String login(Model model){
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("login")
	public String loginValidation(@Valid User user, RedirectAttributes redirect) {
		Iterable<User> allUser = userService.findAll();
		for(User u: allUser) {
			if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
				ProductManagerApplication.userid = u.getId();
				return "redirect:/product";
			}
		}
		return "redirect:/login";
	}
}
