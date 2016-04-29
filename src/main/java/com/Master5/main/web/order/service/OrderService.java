package com.Master5.main.web.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Master5.main.web.order.dao.IngredientDao;
import com.Master5.main.web.order.dao.IngredientTypeDao;
import com.Master5.main.web.order.dao.OrderDao;
import com.Master5.main.web.order.dao.SupplierDao;
import com.Master5.main.web.order.entry.Ingredient;
import com.Master5.main.web.order.entry.IngredientType;
import com.Master5.main.web.order.entry.Orders;
import com.Master5.main.web.order.entry.Supplier;

@Service
public class OrderService   {

	@Autowired
	OrderDao orderDao;
	
	@Autowired
	SupplierDao supplierDao;
	
	@Autowired
	IngredientDao ingredientDao;

	@Autowired
	IngredientTypeDao ingredientTypeDao;

	public List<Orders> query() {
		return orderDao.findAll();
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
	
	public List<Supplier> querySupplier(){
		return supplierDao.findAll();
	}
	
	public Supplier addSupplier(Supplier bean) {
		return supplierDao.saveAndFlush(bean);
	}

	public boolean deleteSupplier(int id) {
		supplierDao.delete(id);
		return true;
	}
	
	public List<Ingredient> queryIngredient(){
		return ingredientDao.findAll();
	}
	
	public Ingredient addIngredient(Ingredient bean) {
		return ingredientDao.saveAndFlush(bean);
	}

	public boolean deleteIngredient(int id) {
		ingredientDao.delete(id);
		return true;
	}
	
	

}
