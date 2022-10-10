package com.greedy.semi.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greedy.semi.member.dto.MemberDTO;
import com.greedy.semi.member.entity.Member;
import com.greedy.semi.member.service.FindPwMail;
import com.greedy.semi.member.service.MemberService;
import com.greedy.semi.member.service.RegisterMail;

@Controller
public class AccountController {

	@Autowired
	RegisterMail registerMail;
	
	@Autowired
	MemberService ms;

	// 임시 패스워드 발송 서비스
	@Autowired
	FindPwMail findPwMail;

	@Autowired
	private PasswordEncoder passwdEncoder;


	// 이메일 인증
	@PostMapping("/regist/mailConfirm")
	@ResponseBody
	String mailConfirm(@RequestParam("email") String email) throws Exception {

		String code = registerMail.sendSimpleMessage(email);
	  System.out.println("인증코드 : " + code);
		return code;
		}
	
	@GetMapping("member/searchPassword")
	String searchPassword() {
		return "member/searchPassword";
	}

	
	
	// 일반 회원 비밀번호 찾기 및 임시 패스워드로 변경
	@PostMapping("/member/findMemberPwd")
	@ResponseBody
	String findMemberPwd(@RequestParam("memberId") String memberId, @RequestParam("name") String name,
			@RequestParam("phone") String phone) throws Exception {
		// System.out.println(mid + " : " + mname + " : " + mphone);
		MemberDTO mdto = ms.findByMemberIdAndNameAndPhone(memberId, name, phone);

		if (mdto != null) {
			// 임시 패스워드 메일 발송 및 변수 저장
			String tempPw = passwdEncoder.encode(findPwMail.sendSimpleMessage(mdto.getEmail()));
			// System.out.println("tempPw : " + tempPw);
			// 임시 패스워드 db 에 저장
			ms.changeTempPw(tempPw, mdto.getMemberId());

			return "변경완료";
		}
		else {
			return "변경실패";
		}
	}


}
