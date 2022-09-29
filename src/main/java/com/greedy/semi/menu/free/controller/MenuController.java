package com.greedy.semi.menu.free.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/free")
public class MenuController {

	@GetMapping(value = {"/", "/main"})
	public String main() {
		
		return "free/main";
	}
	
	@PostMapping("/")
	public String redirectMain() {
		
		return "redirect:/";
	}
	
	@GetMapping(value = {"/make"})
	public String make() {
		
		return "/free/make";
	}
	
	@PostMapping(value="/make")
	public String redirectMake() {
		
		return "redirect:/";
	}
	
	
}
