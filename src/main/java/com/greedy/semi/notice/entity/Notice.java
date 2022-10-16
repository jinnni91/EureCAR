package com.greedy.semi.notice.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.semi.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "NOTICE")
@SequenceGenerator(name = "NOTI_SEQ_GENERATOR", sequenceName = "SEQ_NOTICE_NO", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class Notice {
	
	@Id
	@Column(name = "NOTI_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTI_SEQ_GENERATOR")
	private Long noticeNo;

	@Column(name = "NOTI_TITLE")
	private String noticeTitle;
	
	@Column(name = "NOTI_CONTENT")
	private String noticeContent;
	
	@Column(name = "NOTI_DELETE")
	private String noticeDelete;
	
	@Column(name = "NOTI_DATE")
	private Date noticeDate;
	
	@Column(name = "NOTI_UPDATE")
	private Date noticeUpdate;	
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member writer;
	
	@Column(name = "NOTI_COUNT")
	private int noticeCount;
	
	
	
	

}
