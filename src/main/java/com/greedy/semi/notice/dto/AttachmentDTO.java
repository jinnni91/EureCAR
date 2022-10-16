package com.greedy.semi.notice.dto;

import lombok.Data;

@Data
public class AttachmentDTO {

	private Long attachmentNo;	
	private String originalName;
	private String savedName;
	private String savePath;
	private String fileType;	
	private String attachmentStatus;
	
	
}
