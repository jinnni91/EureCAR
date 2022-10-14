package com.greedy.semi.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.semi.admin.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

	Report findByReportCode(Long reportCode);

	
	
}
