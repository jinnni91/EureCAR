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
	public static final String SORT_BY = "freeNo";
	public static final String ACTIVE_DELETE = "N";
	
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
			list = freeRepository.findBySearchValue(ACTIVE_DELETE, searchValue, pageable);
		} else {
			list = freeRepository.findByFreeDelete(ACTIVE_DELETE, pageable);
		}
		
		return list.map(free -> modelMapper.map(free, FreeDTO.class));
	}
	
	public void registFree(FreeDTO free) {
		
		freeRepository.save(modelMapper.map(free, Free.class));
		
	}

	public FreeDTO selectFreeDetail(Long freeNo) {
		
		Free free = freeRepository.findByFreeNoAndFreeDelete(freeNo, ACTIVE_DELETE);
		free.setFreeCount(free.getFreeCount() + 1);
		
		return modelMapper.map(free, FreeDTO.class);
	}

	public void makeFree(FreeDTO free) {

		freeRepository.save(modelMapper.map(free, Free.class));
		
		
	}
}
