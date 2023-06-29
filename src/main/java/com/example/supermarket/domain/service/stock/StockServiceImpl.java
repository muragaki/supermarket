package com.example.supermarket.domain.service.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supermarket.domain.model.Stock;
import com.example.supermarket.domain.repository.stock.StockRepository;

@Service
public class StockServiceImpl implements StockService {
	@Autowired
	StockRepository stockRepository;
	
	@Override
	public List<Stock> findall() {
		return stockRepository.findAll();
	}

}