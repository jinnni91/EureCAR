package com.greedy.semi.free.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.semi.free.dto.FreeDTO;
import com.greedy.semi.free.entity.Free;
import com.greedy.semi.free.repository.FreeRepository;


@Service
@Transactional
public class FreeService {
	public static final int TEXT_PAGE_SIZE = 10;
	public static final int TEXT_FREE_TYPE = 1;
	public static final String SORT_BY = "freeNo";
	public static final String ACTIVE_STATUS = "Y";
	
	private final FreeRepository freeRepository;
	private final ModelMapper modelMapper;
	
	public FreeService(FreeRepository freeRepository, ModelMapper modelMapper) {
		this.freeRepository = freeRepository;
		this.modelMapper = modelMapper;
	}
	
	public Page<FreeDTO> selectList(int page, String searchValue) {
		
		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).descending());
		Page<Free> list = null;
		
		if(searchValue != null && !searchValue.isEmpty()) {
			list = freeRepository.findBySearchValue(TEXT_FREE_TYPE, ACTIVE_STATUS, searchValue, pageable);
		} else {
			list = freeRepository.findByFreeTypeAndFreeStatus(TEXT_FREE_TYPE, ACTIVE_STATUS, pageable);
		}
		
		return list.map(free -> modelMapper.map(free, FreeDTO.class));
	}
	
	public void registFree(FreeDTO free) {
		
		freeRepository.save(modelMapper.map(free, Free.class));
		
	}
}
