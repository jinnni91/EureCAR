package com.greedy.semi.trade.controller;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.semi.admin.dto.ReportDTO;
import com.greedy.semi.member.dto.MemberDTO;
import com.greedy.semi.trade.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/report")
public class ReportController {

	private final ReportService reportService;
	private final MessageSourceAccessor messageSourceAccessor;
	
	public ReportController(ReportService reportService, MessageSourceAccessor messageSourceAccessor) {
		this.reportService = reportService;
		this.messageSourceAccessor = messageSourceAccessor;
	}
	
	@GetMapping("/regist")
	public String goRegister(Long sellNo, Model model) {
		
		model.addAttribute("sellNo", sellNo);
		
		return "trade/reportRegist";
	}
	
	@PostMapping("/regist")
	public String registReport(ReportDTO report, @AuthenticationPrincipal MemberDTO member, RedirectAttributes rttr) {
		
		log.info("[ReportController] ========================================= ");
		log.info("[ReportController] registReport request : {}", report);
		
		report.setReporter(member);
		reportService.registReport(report);
		
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("report.regist"));
		
		log.info("[ReportController] ========================================= ");
		
		return "redirect:/trade/list";
		
	}
}
