package com.greedy.semi.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.semi.trade.entity.TradeReply;

public interface TradeReplyRepository extends JpaRepository<TradeReply, Long> {

	List<TradeReply> findBySellNoAndReplyDelete(Long sellNo, String replyDelete);
	
	TradeReply findByReplyNo(Long replyNo);
	
}
