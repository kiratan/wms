package com.Master5.main.web.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Master5.main.web.order.dao.IngredientTypeDao;
import com.Master5.main.web.order.dao.OrderDao;
import com.Master5.main.web.order.entry.IngredientType;
import com.Master5.main.web.order.entry.Orders;
import com.Master5.main.web.order.service.IOrderService;

@Service
public class OrderService implements IOrderService {

	@Autowired
	OrderDao orderDao;

	@Autowired
	IngredientTypeDao ingredientTypeDao;

	@Override
	public List<Orders> query() {
		return orderDao.findAll();
	}

	@Override
	public List<Orders> query(Orders orders) {
		return null;
	}

	@Override
	public Orders query(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders add(Orders orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders modify(Orders orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Orders orders) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<IngredientType> queryIngredientType() {
		return ingredientTypeDao.findAll();
	}

	@Override
	public IngredientType addIngredientType(IngredientType bean) {
		return ingredientTypeDao.saveAndFlush(bean);
	}

	@Override
	public boolean deleteIngredientType(int id) {
		ingredientTypeDao.delete(id);
		return true;
	}

}
