package com.greedy.semi.trade.dto;

import java.sql.Date;

import com.greedy.semi.member.dto.MemberDTO;

import lombok.Data;

@Data
public class TradeReplyDTO {
	
	private Long replyNo;
	private Long sellNo;
	private String replyContent;
	private Date replyDate;
	private Date replyUpdate;
	private String replyDelete;
	private MemberDTO memberId;

}
