package com.greedy.semi.admin.dto;
import com.greedy.semi.member.dto.MemberDTO;
import com.greedy.semi.trade.dto.TradeDTO;
import lombok.Data;

@Data
public class ReportDTO {
	
	private Long reportCode;
	private String reportContent;
	private MemberDTO reporter;
	private MemberDTO reported;
	private TradeDTO sell;
	
}