package com.example.supermarket.domain.service.goods;

import java.util.List;

import com.example.supermarket.domain.model.MItem;

public interface GoodsService {
	public List<MItem> findGoods();
	public MItem findItem(String itemcode);
	public void itemregist(MItem item);
}