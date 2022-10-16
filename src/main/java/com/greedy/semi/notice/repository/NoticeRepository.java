package com.greedy.semi.notice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.semi.notice.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>{
	
	
	Page<Notice> findByNoticeDelete(String noticeDelete, Pageable pageable);
	
	
	@Query("SELECT n " +
			 "FROM Notice n " +
			"WHERE n.noticeDelete = :noticeDelete " +
			  "AND (n.noticeTitle LIKE '%' || :searchValue || '%' " +
			   "OR n.noticeContent LIKE '%' || :searchValue || '%')")
	Page<Notice> findBySearchValue(@Param("noticeDelete") String noticeDelete, @Param("searchValue") String searchValue, Pageable pageable);
	
	Notice findByNoticeNoAndNoticeDelete(Long noticeNo, String noticeDelete);


	


	//공지 넘버 찾기
	Notice findByNoticeNo(Long noticeNo);

			
			
	
	
}
