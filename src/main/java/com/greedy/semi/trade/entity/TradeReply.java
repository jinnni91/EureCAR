package com.greedy.semi.trade.entity;

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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SELL_REPLY")
@SequenceGenerator(name = "TRADE_REPLY_SEQ_GENERATOR", sequenceName = "SEQ_SELL_REPLY_NO", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class TradeReply {
	
	@Id
	@Column(name = "REPLY_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRADE_REPLY_SEQ_GENERATOR")
	private Long replyNo;
	
	@Column(name = "REPLY_CONTENT")
	private String replyContent;
	
	@Column(name = "REPLY_DATE")
	private Date replyDate;
	
	@Column(name = "REPLY_UPDATE")
	private Date replyUpdate;
	
	@Column(name = "REPLY_DELETE")
	private String replyDelete;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member memberId;
	
	@Column(name = "SELL_NO")
	private Long sellNo;

}
