package com.example.supermarket.domain.service.sales;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supermarket.domain.model.Sales;
import com.example.supermarket.domain.repository.sales.SalesRepository;

@Service
public class SalesServiceImpl implements SalesService {
	
	@Autowired
	SalesRepository salesRepository;

	@Override
	public void save(Sales sales) {
		salesRepository.save(sales);
	}
	
	@Override
	public List<Sales> finditem(String userid) {
		return salesRepository.findByUseridOrderBySalestimeAsc(userid);
	}
	
	@Override
	public List<Sales> findSalesAll() {
		return salesRepository.findAll();
	}
	
	@Override
	public List<Sales> findSalesDate(LocalDateTime firstDate, LocalDateTime lastDate) {
		return salesRepository.findBySalestimeBetween(firstDate, lastDate);
	}
	
	@Override
	public List<Sales> findUserDate(String user, LocalDateTime firstDate, LocalDateTime lastDate) {
		return salesRepository.findByUseridAndSalestimeBetween(user, firstDate, lastDate);
	}
}