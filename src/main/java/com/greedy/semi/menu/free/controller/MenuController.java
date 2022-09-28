package com.greedy.semi.menu.free.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.data.common.Pagenation;
import com.greedy.data.common.PagingButtonInfo;
import com.greedy.data.menu.dto.CategoryDTO;
import com.greedy.data.menu.dto.MenuDTO;
import com.greedy.data.menu.service.MenuService;

import lombok.extern.slf4j.Slf4j;

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
