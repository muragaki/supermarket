package com.example.supermarket.domain.service.sales;

import java.time.LocalDateTime;
import java.util.List;

import com.example.supermarket.domain.model.Sales;

public interface SalesService {
	public void save(Sales sales);
	public List<Sales> findSalesAll();
	public List<Sales> finditem(String userid);
	public List<Sales> findSalesDate(LocalDateTime firstDate, LocalDateTime lastDate);
	public List<Sales> findUserDate(String user, LocalDateTime firstDate, LocalDateTime lastDate);
}