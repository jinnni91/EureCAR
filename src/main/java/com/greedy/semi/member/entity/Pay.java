package com.greedy.semi.member.entity;



import java.util.Date;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PAY")
@SequenceGenerator(
		name = "PAY_SEQ_GENERATOR",
		sequenceName = "SEQ_PAY_NO",
		initialValue = 1, allocationSize = 1)
@DynamicInsert /* insert 동작 시 null인 필드를 제외하고 동작한다 */
public class Pay {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAY_SEQ_GENERATOR")
	@Column(name = "PAY_NO")
    private int payNo;
	
	@Column(name = "PAT_AMT")
    private int payAmt;
	
	@Column(name = "PAT_DATE")
    private Date payDate;
	
	@ManyToOne
	@JoinColumn(name = "ORDER_CODE")
	private OrderInfo orderCode;
		
	

   
}
