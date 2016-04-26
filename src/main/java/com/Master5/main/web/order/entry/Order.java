package com.Master5.main.web.order.entry;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

/**
 * 订单
 * @author Bada Lee
 *
 */
public class Order {
	
	@OneToMany(mappedBy="person",cascade=CascadeType.ALL)
	private List<Ingredient> goods;

}
