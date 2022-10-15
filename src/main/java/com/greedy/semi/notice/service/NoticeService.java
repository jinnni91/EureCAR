package com.greedy.semi.notice.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.semi.notice.dto.NoticeDTO;
import com.greedy.semi.notice.dto.ReplyDTO;
import com.greedy.semi.notice.entity.Notice;
import com.greedy.semi.notice.entity.Reply;
import com.greedy.semi.notice.repository.NoticeRepository;
import com.greedy.semi.notice.repository.ReplyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class NoticeService {
	
	public static final int TEXT_PAGE_SIZE = 10;
	public static final int TEXT_NOTICE_TYPE = 1;
	public static final String SORT_BY = "noticeNo";
	public static final String ACTIVE_DELETE = "N";
	
	private final NoticeRepository noticeRepository;
	private final ReplyRepository replyRepository;
	private final ModelMapper modelMapper;
	
	public NoticeService(NoticeRepository noticeRepository, ReplyRepository replyRepository, ModelMapper modelMapper) {
		this.noticeRepository = noticeRepository;
		this.replyRepository = replyRepository;
		this.modelMapper = modelMapper;
	}

	public Page<NoticeDTO> selectNoticeList(int page, String searchValue) {
		
		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).descending());
		Page<Notice> noticeList = null;
		
		if(searchValue != null && !searchValue.isEmpty()) {
			noticeList = noticeRepository.findBySearchValue(ACTIVE_DELETE, searchValue, pageable);
		} else {
			noticeList = noticeRepository.findByNoticeDelete (ACTIVE_DELETE, pageable);
		}
		
		log.info("noticeList: {}", noticeList.getContent());
		
		
		return noticeList.map(notice -> modelMapper.map(notice, NoticeDTO.class));
	}

	public NoticeDTO selectNoticeDetail(Long noticeNo) {
		
		Notice notice = noticeRepository.findByNoticeNoAndNoticeDelete (noticeNo, ACTIVE_DELETE);
		notice.setNoticeCount(notice.getNoticeCount() + 1);
		
		return modelMapper.map(notice, NoticeDTO.class);
	}

	public void registNotice(NoticeDTO notice) {
		
		
		noticeRepository.save(modelMapper.map(notice, Notice.class));
		
	}
	

	
	

	

	

	public void registReply(ReplyDTO registReply) {
		
		replyRepository.save(modelMapper.map(registReply, Reply.class));
		
	}

	public List<ReplyDTO> loadReply(ReplyDTO loadReply) {
		
		List<Reply> replyList 
			= replyRepository.findByNoticeNoAndReplyDelete (loadReply.getNoticeNo(), ACTIVE_DELETE);
		
		return replyList.stream().map(reply -> modelMapper.map(reply, ReplyDTO.class)).collect(Collectors.toList());
	}

	public void removeReply(ReplyDTO removeReply) {
		
		Reply foundReply = replyRepository.findByReplyNo(removeReply.getReplyNo());
		foundReply.setReplyDelete("Y");
		
	}

	public NoticeDTO getPost(Long noticeNo) {
		
		return null;
	}

	//글 수정
	public void modifyNotice(NoticeDTO updateNotice) {
		Notice savedNotice = noticeRepository.findByNoticeNo(updateNotice.getNoticeNo());
		savedNotice.setNoticeNo(updateNotice.getNoticeNo());
		savedNotice.setNoticeTitle(updateNotice.getNoticeTitle());
		savedNotice.setNoticeContent(updateNotice.getNoticeContent());
	}

	//글 삭제
	public void deleteNotice(NoticeDTO removeNotice) {

		Notice selectedNotice = noticeRepository.findByNoticeNo(removeNotice.getNoticeNo());
		selectedNotice.setNoticeDelete("Y");
	}

//	public void modifyReply(ReplyDTO modifyReply) {
//		Reply foundReply = replyRepository.findByReplyNo(modifyReply.getReplyNo());
//		
//		replyRepository.save(modelMapper.map(modifyReply, Reply.class));
//	}

	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}