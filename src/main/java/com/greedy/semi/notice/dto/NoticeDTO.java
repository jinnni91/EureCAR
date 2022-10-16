package com.greedy.semi.notice.dto;

import java.sql.Date;
import java.util.List;

import com.greedy.semi.member.dto.MemberDTO;

import lombok.Data;

@Data
public class NoticeDTO {

	private Long noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private MemberDTO writer;
	private int noticeCount;
	private Date noticeDate;
	private Date noticeUpdate;
	private String noticeDelete;
	private List<AttachmentDTO> attachmentList;
}
