package com.Master5.main.web.order.service;

import java.util.List;

import com.Master5.main.web.order.entry.IngredientType;
import com.Master5.main.web.order.entry.Orders;

public interface IOrderService {

	public List<Orders> query();

	public List<Orders> query(Orders orders);

	public Orders query(int id);

	public Orders add(Orders bean);

	public Orders modify(Orders orders);

	public boolean delete(Orders orders);

	public boolean delete(int id);
	
	/**
	 * 
	 */

	public List<IngredientType> queryIngredientType();
	
	public IngredientType addIngredientType(IngredientType bean);
	
	public boolean deleteIngredientType(int id);
	
}
