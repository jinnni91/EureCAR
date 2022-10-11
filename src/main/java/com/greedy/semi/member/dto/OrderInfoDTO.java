package com.greedy.semi.member.dto;


import com.greedy.semi.trade.dto.TradeDTO;

import lombok.Data;

@Data
public class OrderInfoDTO {
	private int orderCode;
	private MemberDTO memberId;
	private ProductDTO productCode;
	private TradeDTO sellNo;
	
}
