package com.greedy.semi.member.entity;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
@DynamicInsert /* insert 동작 시 null인 필드를 제외하고 동작한다 */
public class Product {

	@Id
	@Column(name = "PRODUCT_CODE")
    private int productCode;
	
	@Column(name = "PRODUCT_NAME")
    private String productName;
	
	@Column(name = "PRODUCT_PRICE")
    private int productPrice;
	

	

   
}
