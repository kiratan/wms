package com.Master5.main.web.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Master5.main.web.order.dao.IngredientTypeDao;
import com.Master5.main.web.order.dao.OrderDao;
import com.Master5.main.web.order.entry.IngredientType;
import com.Master5.main.web.order.entry.Orders;

@Service
public class OrderService   {

	@Autowired
	OrderDao orderDao;

	@Autowired
	IngredientTypeDao ingredientTypeDao;

	public List<Orders> query() {
		return orderDao.findAll();
	}

	public List<Orders> query(Orders orders) {
		return null;
	}

	public Orders query(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Orders add(Orders orders) {
		// TODO Auto-generated method stub
		return null;
	}

	public Orders modify(Orders orders) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean delete(Orders orders) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<IngredientType> queryIngredientType() {
		return ingredientTypeDao.findAll();
	}

	public IngredientType addIngredientType(IngredientType bean) {
		return ingredientTypeDao.saveAndFlush(bean);
	}

	public boolean deleteIngredientType(int id) {
		ingredientTypeDao.delete(id);
		return true;
	}

}
