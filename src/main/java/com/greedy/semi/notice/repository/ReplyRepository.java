package com.greedy.semi.notice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.semi.notice.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	
	List<Reply> findByNoticeNoAndReplyDelete(Long NoticeNo, String replyDelete);
	
	Reply findByReplyNo(Long replyNo);

}
