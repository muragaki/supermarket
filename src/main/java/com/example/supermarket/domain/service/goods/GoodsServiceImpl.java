package com.example.supermarket.domain.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supermarket.domain.model.MItem;
import com.example.supermarket.domain.repository.goods.GoodsRepository;

import jakarta.transaction.Transactional;

/**
 * 商品サービス.
 * 
 * @author Murahgaki
 * @version 1.0
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	GoodsRepository goodsRepository;
	
	/**
	 * 全商品情報取得
	 * 
	 * @return
	 */
	@Override
	public List<MItem> findGoods() {
		return goodsRepository.findAllByOrderByItemcode();
	}
	
	/**
	 * 商品情報取得
	 * 
	 * @param itemcode
	 * @return
	 */
	@Override
	public MItem findItem(String itemcode) {
		return goodsRepository.findById(itemcode).get();
	}
	
	/**
	 * 商品情報登録
	 * 
	 * @param item
	 */
	@Override
	public void itemregist(MItem item) {
		goodsRepository.save(item);
	}
}