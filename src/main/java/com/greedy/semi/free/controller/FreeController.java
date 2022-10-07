package com.greedy.semi.free.controller;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greedy.semi.free.common.Pagenation;
import com.greedy.semi.free.common.PagingButtonInfo;
import com.greedy.semi.free.dto.FreeDTO;
import com.greedy.semi.free.service.FreeService;


@Controller
@RequestMapping("/free")
public class FreeController {

	private final FreeService freeService;
	private final MessageSourceAccessor messageSourceAccesor;
	
	public FreeController(FreeService freeService, MessageSourceAccessor messageSourceAccesor) {
		this.freeService = freeService;
		this.messageSourceAccesor = messageSourceAccesor;
	}
	
	@GetMapping("list")
	public String list(@RequestParam(defaultValue="1") int page, 
			@RequestParam(required=false) String searchValue, Model model) {

		Page<FreeDTO> list = freeService.selectList(page, searchValue);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(list);
		

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		if(searchValue != null && !searchValue.isEmpty()) {
			model.addAttribute("searchValue", searchValue);
		}
	
		return "free/list";
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
