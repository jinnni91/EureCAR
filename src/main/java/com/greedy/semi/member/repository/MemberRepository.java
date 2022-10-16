package com.greedy.semi.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.semi.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	Optional<Member> findByMemberIdAndMemberStatus(String memberId, String memberStatus);
	
	Member findIdByNameAndEmailAndMemberStatus(String name, String email, String memberStatus);
	
	Member findIdByNameAndEmail(String name, String email);
	
	Member findByMemberId(String memberId);

	Member findByMemberIdAndNameAndPhoneAndMemberStatus(String memberId, String name, String phone, String memberStatus);

	

}
