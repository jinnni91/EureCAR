package com.greedy.semi.admin.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.semi.member.entity.Member;
import com.greedy.semi.trade.entity.Trade;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="REPORT")
@SequenceGenerator(name = "REPORT_SEQ_GENERATOR", sequenceName = "SEQ_REPORT_NO", initialValue = 1, allocationSize = 1)
public class Report {
	
	@Id
	@Column(name = "REPORT_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPORT_SEQ_GENERATOR")
	private Long reportCode;
	
	@Column(name = "REPORT_CONTENT")
	private String reportContent;
	
	@ManyToOne
	@JoinColumn(name = "REPORTER_ID")
    private Member reporter;
	
	@ManyToOne
	@JoinColumn(name = "REPORTED_ID")
	private Member reported;
	
	@ManyToOne
	@JoinColumn(name= "SELL_NO")
	private Trade sell;
	
}