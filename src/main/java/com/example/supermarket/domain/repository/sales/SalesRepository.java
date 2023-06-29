package com.example.supermarket.domain.repository.sales;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermarket.domain.model.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer> {
	List<Sales> findByUseridOrderBySalestimeAsc(String userid);
	List<Sales> findBySalestimeBetween(LocalDateTime firstDate, LocalDateTime lastDate);
	List<Sales> findByUseridAndSalestimeBetween(String userid, LocalDateTime firstDate, LocalDateTime lastDate);
	Sales readByItemcode(String itemcode);
	Sales readByItemcodeAndUserid(String itemcode, String userid);
	
}