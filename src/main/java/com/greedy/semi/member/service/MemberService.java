package com.greedy.semi.member.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.semi.member.dto.MemberDTO;
import com.greedy.semi.member.entity.Member;
import com.greedy.semi.member.repository.MemberRepository;


@Service
@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;
	
	public MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
	}

	public boolean selectMemberById(String memberId) {
		
		return memberRepository.findByMemberIdAndMemberStatus(memberId, "N").isPresent();
	}

	public void registMember(MemberDTO member) {
		
		memberRepository.save(modelMapper.map(member, Member.class));
	}

	public String findIdByNameAndEmail(String name, String email) {
		
		Member member = memberRepository.findIdByNameAndEmail(name, email);
		
		 if (member == null)
	            return null;
	       return member.getMemberId();
	    }

	}
	



