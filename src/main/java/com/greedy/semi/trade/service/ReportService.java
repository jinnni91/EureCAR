package com.greedy.semi.trade.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.semi.admin.dto.ReportDTO;
import com.greedy.semi.admin.entity.Report;
import com.greedy.semi.trade.repository.ReportRepository;
import com.greedy.semi.trade.repository.TradeRepository;

@Service
@Transactional
public class ReportService {
	
	private final TradeRepository tradeRepository;
	private final ReportRepository reportRepository;
	private final ModelMapper modelMapper;
	
	public ReportService(ReportRepository reportRepository, ModelMapper modelMapper, TradeRepository tradeRepository) {
		
		this.reportRepository = reportRepository;
		this.modelMapper = modelMapper;
		this.tradeRepository = tradeRepository;
	}

	public void registReport(ReportDTO reportDto) {
		
		Report report = modelMapper.map(reportDto, Report.class);
		report.setReported(tradeRepository.findById(report.getSell().getSellNo()).get().getMember());
		reportRepository.save(report);
		
		
	}

}
