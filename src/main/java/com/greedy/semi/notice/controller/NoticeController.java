package com.greedy.semi.notice.controller;



import java.util.List;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.semi.member.dto.MemberDTO;
import com.greedy.semi.notice.common.Pagenation;
import com.greedy.semi.notice.common.PagingButtonInfo;
import com.greedy.semi.notice.dto.NoticeDTO;
import com.greedy.semi.notice.dto.ReplyDTO;
import com.greedy.semi.notice.service.NoticeService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	private final NoticeService noticeService;
	private final MessageSourceAccessor messageSourceAccesor;
	
	
	public NoticeController(NoticeService noticeService, MessageSourceAccessor messageSourceAccesor) {
		this.noticeService = noticeService;
		this.messageSourceAccesor = messageSourceAccesor;
	}
	
	
	
	
	@GetMapping("/noticeList")
	public String noticeList(@RequestParam(defaultValue="1") int page,
			@RequestParam(required=false) String searchValue, Model model) {
		
		log.info("[NoticeController] ========================================= ");
		log.info("[NoticeController] param page : {}", page);
		log.info("[NoticeController] param searchValue : {}", searchValue);
		
		Page<NoticeDTO> noticeList = noticeService.selectNoticeList(page, searchValue);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(noticeList);
				
		log.info("[NoticeController] noticeList : {}", noticeList);
		log.info("[NoticeController] paging : {}", paging);		
				
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("paging", paging);
		if(searchValue != null && !searchValue.isEmpty()) {
			model.addAttribute("searchValue", searchValue);
		}
			
		log.info("[NoticeController] ========================================= ");
		
				return "notice/noticeList";
	
	}
	
	@GetMapping("/detail")
	public String selectNoticeDetail(Model model, Long noticeNo) {
		
		log.info("[NoticeController] ========================================= ");
		log.info("[NoticeController] boardNo : {}", noticeNo);
		
		NoticeDTO notice = noticeService.selectNoticeDetail(noticeNo);
		
		log.info("[NoticeController] board : {}", notice);
		
		model.addAttribute("notice", notice);
		
		log.info("[NoticeController] ========================================= ");

		
		return "notice/noticeDetail";
	}
	
	
	@GetMapping("/regist")
	public String goRegister() {
		return "notice/noticeRegist";
	}
	
	@PostMapping("/regist")
	public String registNoitce(NoticeDTO notice, @AuthenticationPrincipal MemberDTO member, RedirectAttributes rttr) {
		
		log.info("[NoticeController] ========================================= ");
		log.info("[NoticeController] registBoard request : {}", notice);
		
		notice.setWriter(member);
		noticeService.registNotice(notice);
		
		rttr.addFlashAttribute("message", messageSourceAccesor.getMessage("notice.regist"));
		
		log.info("[NoticeController] ========================================= ");

		
		return "redirect:/notice/noticeList";
	}
	
	//공지 수정
	
	@GetMapping("/update")
	public String goUpdate(Long noticeNo, Model model) {
		
		log.info("[NoticeController] =================================================================== ");
		log.info("[NoticeController] parameter noticeNo : {}", noticeNo);
		
		model.addAttribute("notice", noticeService.selectNoticeDetail(noticeNo));
		
		log.info("[NoticeController] =================================================================== ");
		
		return "notice/noticeUpdate";
		
	}
	
	@PostMapping("/update")
	public String modifyNotice(@ModelAttribute NoticeDTO updateNotice, RedirectAttributes rttr) {
		
		log.info("[NoticeController] =================================================================== ");
		
		log.info("[NoticeController] updateNotice request Notice : {}", updateNotice);
		
		noticeService.modifyNotice(updateNotice);
		
		rttr.addFlashAttribute("message", 
				messageSourceAccesor.getMessage("notice.modify"));
		
		log.info("[NoticeController] =================================================================== ");
		
//		return "redirect:/notice/noticeList";
		return "redirect:/notice/detail?noticeNo=" + updateNotice.getNoticeNo();
		
		
	}
	
	
	//공지 삭제
	@GetMapping("/delete")
	public String deleteNotice(NoticeDTO notice, RedirectAttributes rttr) {

		noticeService.deleteNotice(notice);
	
		rttr.addFlashAttribute("message", messageSourceAccesor.getMessage("notice.delete"));
		
		return "redirect:/notice/noticeList";
	}
	
	
	
	
	@PostMapping("/registReply")
	public ResponseEntity<String> registReply(@RequestBody ReplyDTO registReply,
			@AuthenticationPrincipal MemberDTO member) {
		
		log.info("[NoticeController] ========================================= ");
		log.info("[NoticeController] registReply : {}", registReply);
			
		registReply.setMemberId(member);
		
		
		noticeService.registReply(registReply);
		
		log.info("[NoticeController] ========================================= ");

		
		return ResponseEntity.ok("댓글 등록 완료");
		
	}	
	
	
	@GetMapping("/loadReply")
	public ResponseEntity<List<ReplyDTO>> loadReply(ReplyDTO loadReply) {
		
		log.info("[NoticeController] ========================================= ");
		log.info("[BoardController] loadReply : {}", loadReply);
		
		
		List<ReplyDTO> replyList = noticeService.loadReply(loadReply);
		
		log.info("[NoticeController] replyList : {}", replyList);
		log.info("[NoticeController] ========================================= ");
						
		return ResponseEntity.ok(replyList);
		
	}
	
	@PostMapping("/removeReply")
	public ResponseEntity<String> removeReply(@RequestBody ReplyDTO removeReply) {
		
		log.info("[NoticeController] ========================================= ");
		log.info("[NoticeController] loadReply : {}", removeReply);
		
		noticeService.removeReply(removeReply);
				
		log.info("[NoticeController] ========================================= ");

		
		return ResponseEntity.ok("댓글 삭제 완료");
	}
	
}
//	@PostMapping("/modifyReply")
//	public ResponseEntity<String> modifyReply(@RequestBody ReplyDTO modifyReply,
//			@AuthenticationPrincipal MemberDTO member) {
//		
//		log.info("[NoticeController] ========================================= ");
//		log.info("[NoticeController] registReply : {}", modifyReply);
//			
//		modifyReply.setMemberId(member);
//		
//		
//		noticeService.modifyReply(modifyReply);
//		
//		log.info("[NoticeController] ========================================= ");
//
//		
//		return ResponseEntity.ok("댓글 등록 완료");
//		
//	}	
//	
//}
