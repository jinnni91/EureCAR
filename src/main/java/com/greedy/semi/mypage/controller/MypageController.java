package com.greedy.semi.mypage.controller;

import java.util.Optional;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.semi.common.Pagenation;
import com.greedy.semi.common.PagingButtonInfo;
import com.greedy.semi.free.dto.FreeDTO;
import com.greedy.semi.free.service.FreeService;
import com.greedy.semi.member.dto.MemberDTO;
import com.greedy.semi.member.entity.Member;
import com.greedy.semi.member.service.AuthenticationService;
import com.greedy.semi.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	 private final MessageSourceAccessor messageSourceAccessor;
	    private final MemberService memberService;
	    private final AuthenticationService authenticationService;
	    private final FreeService freeService;
	    private final PasswordEncoder passwordEncoder;
	    
	    public MypageController(MessageSourceAccessor messageSourceAccessor, 
	                            MemberService memberService, 
	                            AuthenticationService authenticationService, 
	                            FreeService freeService,
	                            PasswordEncoder passwordEncoder) {
	        
	        this.authenticationService = authenticationService;
	        this.memberService = memberService;
	        this.messageSourceAccessor = messageSourceAccessor;
	        this.freeService = freeService;
	        this.passwordEncoder = passwordEncoder;
	    }   
	    
	    @GetMapping("/mypage")
	    public String myPage() {
	        
	        return "mypage/mypage";
	    }
	    
	    @GetMapping("/mylist")
	    public String myList(@RequestParam(defaultValue="1") int page,
	                         @RequestParam(required=false) String searchValue,
	                         Model model) {
	        
	        Page<FreeDTO> list = freeService.selectList(page, searchValue);
	        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(list);
	        
	        model.addAttribute("list", list);
	        model.addAttribute("paging", paging);
	        
	        if(searchValue != null && !searchValue.isEmpty()) {
	            
	            model.addAttribute("searchValue", searchValue);
	        }
	          
	        return "mypage/mylist";
	    }
	    
	    @GetMapping("/delete")
	    public String deleteMember(@AuthenticationPrincipal MemberDTO member, 
	                               RedirectAttributes rttr) {
	        memberService.removeMember(member);
	        SecurityContextHolder.clearContext();
	        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.delete"));
	    
	        return "redirect:/";
	    }
	    
	    @GetMapping("/update")
	    public String goModifyMember() {
	        
	        return "mypage/update";
	    }
	    
	    @PostMapping("/update")
	    public String modifyMember(@ModelAttribute MemberDTO updateMember,
	                               @RequestParam String zipCode, 
	                               @RequestParam String address1, 
	                               @RequestParam String address2,
	                               @AuthenticationPrincipal MemberDTO loginMember,
	                               RedirectAttributes rttr) {
	        
	        String address = zipCode + "$" + address1 + "$" + address2;
	        
	        updateMember.setAddress(address);
	        updateMember.setPhone(updateMember.getPhone().replace("-", ""));
	        updateMember.setMemberId(loginMember.getMemberId());
	        
	        memberService.modifyMember(updateMember);
	        
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(authentication, loginMember.getMemberId()));
	        
	        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.modify"));
	        
	        return "redirect:/";
	        
	    }
	    
	    protected Authentication createNewAuthentication(Authentication currentAuth, String memberId) {
	            
	        UserDetails newPrincipal = authenticationService.loadUserByUsername(memberId);
	        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, currentAuth.getCredentials(), newPrincipal.getAuthorities());
	        newAuth.setDetails(currentAuth.getDetails());
	        return newAuth;
	            
	    }
	    
	}