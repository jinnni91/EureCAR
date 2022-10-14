package com.greedy.semi.free.dto;

import java.sql.Date;

import com.greedy.semi.member.dto.MemberDTO;

import lombok.Data;

@Data
public class FreeReplyDTO {
	
    private Long replyNo;
    private String replyContent;
    private String replyDelete;
    private Date replyDate;		            
    private Date replyUpdate;		      
    private Long freeNo;
    private MemberDTO memberId;

}
