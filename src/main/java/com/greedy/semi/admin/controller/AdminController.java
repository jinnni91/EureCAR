package com.greedy.semi.admin.controller;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greedy.semi.admin.service.AdminMemberService;
import com.greedy.semi.common.Pagenation;
import com.greedy.semi.common.PagingButtonInfo;
import com.greedy.semi.member.dto.MemberDTO;
import com.greedy.semi.member.service.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final MessageSourceAccessor messageSourceAccessor;
    private final AdminMemberService memberService;
	private final AuthenticationService authenticationService;

	public AdminController(MessageSourceAccessor messageSourceAccessor, AdminMemberService memberService, AuthenticationService authenticationService) {
		
		this.authenticationService = authenticationService;
        this.memberService = memberService;
        this.messageSourceAccessor = messageSourceAccessor;
	}	
		
	
	
	@GetMapping("/search")
	public String goSearch(@RequestParam(defaultValue="1") int page, 
			@RequestParam(required=false) String searchValue, Model model) {
		
		log.info("[AdminController] ========================================= ");
		log.info("[AdminController] param page : {}", page);
		log.info("[AdminController] param searchValue : {}", searchValue);
		
		Page<MemberDTO> memberList =  memberService.selectMemberList(page, searchValue);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(memberList);
		
		log.info("[MemberController] memberList : {}", memberList);
		log.info("[MemberController] paging : {}", paging);
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("paging", paging);
		if(searchValue != null && !searchValue.isEmpty()) {
			model.addAttribute("searchValue", searchValue);
		}
			log.info("[MemberController] ========================================= ");
			
			return "admin/search";
		}
		
	
	
//	@GetMapping("/search")
//		public String goSearch() {
//		return "/admin/search";
//	}

	@GetMapping("/profit")
	public String goProfit() {
		
		return "admin/profit";
	}	
	
	@GetMapping("/warnedmember")
	public String warnedMember() {
		
		return "admin/warnedmember";
	}	
		
		

	

	

	
	
	
	
	
	
	
}	