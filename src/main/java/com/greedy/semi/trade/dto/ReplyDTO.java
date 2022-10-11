package com.greedy.semi.trade.dto;

import java.sql.Date;

import com.greedy.semi.member.dto.MemberDTO;

import lombok.Data;

@Data
public class ReplyDTO {

	private Long replyNo;
	private String replyContent;
	private Date replyDate;
	private Date replyUpdate;
	private String replyDelete;
	private MemberDTO memberId;
	private TradeDTO sellNo;
	private Long refReplyNo;
	
}
