package com.greedy.semi.member.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.semi.member.dto.PayDTO;
import com.greedy.semi.member.entity.Pay;
import com.greedy.semi.member.repository.PayRepository;


@Service
@Transactional
public class PayService {
	
	private final PayRepository payRepository;
	private final ModelMapper modelMapper;
	
	public PayService(PayRepository payRepository, ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.payRepository=payRepository;
	}

	public void payAmount(PayDTO pay)
	{
		payRepository.save(modelMapper.map(pay, Pay.class));
	}

	
	
	
	
	
	
	
	
}
