package com.greedy.semi.member.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.semi.member.dto.MemberDTO;
import com.greedy.semi.member.dto.OrderInfoDTO;
import com.greedy.semi.member.dto.PayDTO;
import com.greedy.semi.member.dto.ProductDTO;
import com.greedy.semi.member.service.PayService;
import com.greedy.semi.trade.dto.TradeDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PayController {

	private final PayService payService;
	private final MessageSourceAccessor messageSourceAccessor;

	private PayController(MessageSourceAccessor messageSourceAccessor, PayService payService) {
		this.payService = payService;
		this.messageSourceAccessor = messageSourceAccessor;

	}

	@ResponseBody
	@PostMapping("/user/mypage")
	public String chargetPoint(RedirectAttributes rttr, @RequestParam("amount") int payAmt,
			@AuthenticationPrincipal MemberDTO member, @RequestParam("sellNo") Long sellNo) {

		PayDTO pay = new PayDTO();
		OrderInfoDTO order = new OrderInfoDTO();
		TradeDTO trade = new TradeDTO();
		ProductDTO product = new ProductDTO();

		Date date = new Date();
		long timeInMilliSeconds = date.getTime();
		java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);
		System.out.println("SQL Date: " + sqlDate);
		log.info(sqlDate.toString());

		String today = null;
		Date time = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();

		/* OrderInfo 저장 */
		/* 넘겨받은 sellNo 저장 */
		trade.setSellNo(sellNo);

		/* MemberId저장 */
		order.setMember(member);

		/* 금액 별로 상품 DTO 가져와서 productCode저장 */

		if (payAmt < 10000) {
			trade.setPayStatus("N");
		} else {
			trade.setPayStatus("Y");
			if (payAmt == 10000) {
				product.setProductCode(1);
			} else if (payAmt == 20000) {
				product.setProductCode(2);
			} else {
				product.setProductCode(3);
			}
		}

		payService.updatePayStatus(trade);

		cal.setTime(time);

		if (product.getProductCode() == 1) {
			cal.add(Calendar.DATE, 7); 

		} else if (product.getProductCode() == 2) {
			cal.add(Calendar.DATE, 15); 
		} else if (product.getProductCode() == 3) {
			cal.add(Calendar.DATE, 30);
		}

		today = sdformat.format(cal.getTime());
		java.sql.Date expirationDate = java.sql.Date.valueOf(today);

		order.setProduct(product);
		order.setTrade(trade);
		order.setExpirationDate(expirationDate);

		/* pay OrderInfoDTO 저장 */
		pay.setOrderInfo(order);
		/* pay Amount 저장 */
		pay.setPayAmt(payAmt);
		/* pay date 저장 */

		pay.setPayDate(sqlDate);

		payService.payAmount(pay);
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("trade.regist"));

		return "redirect:/";
	}
}
