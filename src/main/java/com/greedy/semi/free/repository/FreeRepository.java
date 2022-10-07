package com.greedy.semi.free.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.semi.free.entity.Free;

public interface FreeRepository extends JpaRepository<Free, Long> {


	Page<Free> findByFreeTypeAndFreeStatus(int freeType, String freeStatus, Pageable pageable);
	

	@Query("SELECT b " +
	         "FROM Free b " +
			"WHERE b.freeType = :freeType " +
	          "AND b.freeStatus = :freeStatus " +
			  "AND (b.freeTitle LIKE '%' || :searchValue || '%' " +
			   "OR b.freeContent LIKE '%' || :searchValue || '%')")
	Page<Free> findBySearchValue(@Param("freeType") int freeType, @Param("freeStatus") String freeStatus, @Param("searchValue") String searchValue, Pageable pageable);

	Free findByFreeNoAndFreeTypeAndFreeStatus(Long freeNo, int freeType, String freeStatus);

	

}
