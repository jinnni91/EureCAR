package com.greedy.semi.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/member")
public class MemberController {

	
	@GetMapping("/login")
	public String loginForm(Model model) {
		
		return "member/login";

	}

	@GetMapping("/regist")
	public String regist(Model model) {
		
		return "member/regist";

	}
		
	@GetMapping("/searchId")
	public String searchId(Model model) {
		
		return "member/searchId";

	}
	
	@GetMapping("/searchPassword")
	public String searchPassword(Model model) {
		
		return "member/searchPassword";

	}
	
	@PostMapping("/changePassword")
	public String changePassword(Model model) {
		
		return "member/changePassword";
	}
	
}

