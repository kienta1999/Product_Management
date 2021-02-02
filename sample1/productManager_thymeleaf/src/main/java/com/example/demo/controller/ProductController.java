package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.ProductManagerApplication;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/product")
	public String index(Model model) {
		if(ProductManagerApplication.userid == null) {
			return "redirect:/login";
		}
		model.addAttribute("products", productService.findAll());
		return "list";
	}

	@GetMapping("/product/create")
	public String create(Model model) {
		if(ProductManagerApplication.userid == null) {
			return "redirect:/login";
		}
		model.addAttribute("product", new Product());
		return "form";
	}

	@GetMapping("/product/{id}/edit")
	public String edit(@PathVariable int id, Model model) {
		if(ProductManagerApplication.userid == null) {
			return "redirect:/login";
		}
		model.addAttribute("product", productService.findOne(id));
		return "form";
	}

	@PostMapping("/product/save")
	public String save(@Valid Product product, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "redirect:/login";
		}
		productService.save(product);
		redirect.addFlashAttribute("success", "Saved product successfully!");
		return "redirect:/product";
	}

	@GetMapping("/product/{id}/delete")
	public String delete(@PathVariable int id, RedirectAttributes redirect) {
		if(ProductManagerApplication.userid == null) {
			return "redirect:/login";
		}
		Product product = productService.findOne(id); 
                productService.delete(product);
		redirect.addFlashAttribute("success", "Deleted product successfully!");
		return "redirect:/product";
	}

	@GetMapping("/product/search")
	public String search(@RequestParam("s") String s, Model model) {
		if(ProductManagerApplication.userid == null) {
			return "redirect:/login";
		}
		if (s.equals("")) {
			return "redirect:/product";
		}

		model.addAttribute("products", productService.search(s));
		return "list";
	}
	
}
