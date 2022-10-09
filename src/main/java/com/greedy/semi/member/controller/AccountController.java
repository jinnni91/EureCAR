package com.greedy.semi.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.greedy.semi.member.service.RegisterMail;

@Controller
public class AccountController {
	
//회원가입 메일 서비스
@Autowired
RegisterMail registerMail;

// 이메일 인증
@PostMapping("/regist/mailConfirm")
@ResponseBody
String mailConfirm(@RequestParam("email") String email) throws Exception {

	String code = registerMail.sendSimpleMessage(email);
  System.out.println("인증코드 : " + code);
	return code;
	}
	
}