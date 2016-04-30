package com.Master5.main.web.order.Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Master5.main.utils.constant.Key;
import com.Master5.main.utils.constant.MsgKey;
import com.Master5.main.web.order.entry.Ingredient;
import com.Master5.main.web.order.entry.IngredientType;
import com.Master5.main.web.order.entry.Orders;
import com.Master5.main.web.order.entry.OrdersIngredient;
import com.Master5.main.web.order.entry.Supplier;
import com.Master5.main.web.order.service.OrderService;
import com.Master5.main.web.user.entry.User;

@Controller
@RequestMapping(value = "order")
public class OrdersController {

	@Autowired
	OrderService orderService;

	@RequestMapping(value = { "", "listOrders" })
	public String listOrders(Model model) {

		List<Orders> list = orderService.queryOrders();

		model.addAttribute("list", list);

		return "order/listOrders";
	}
	
	@RequestMapping(value = "addOrders", method = RequestMethod.POST)
	public String addOrders(Orders bean,int[] ingredientId, int[] amount, RedirectAttributes redirectAttributes) {
		
		
		List<OrdersIngredient> detail=new ArrayList<>();
		
		for(int i=0;i<amount.length;i++){
			
			if(amount[i]==0){
				continue;
			}
			
			OrdersIngredient ordersIngredient=new OrdersIngredient();
			ordersIngredient.setAmount(amount[i]);
			ordersIngredient.setIngredientId(orderService.queryIngredient(ingredientId[i]));
			
			detail.add(ordersIngredient);

		}
		
		bean.setDetail(detail);
		bean.setBuyyer((User)SecurityUtils.getSubject().getSession().getAttribute(Key.LOGINED));
		bean.setButtime(Calendar.getInstance().getTime());
		bean.setCreatetime(Calendar.getInstance().getTime());
		
		orderService.addOrders(bean);
		return "redirect:listOrders";
	}
	
	@RequestMapping(value = "delOrders/{id}")
	public String delOrders(@PathVariable int id, Model model) {
		try {
			orderService.deleteOrders(id);
		} catch (JpaSystemException e) {
			List<String> list = new ArrayList<>();
			list.add("删除失败，有相关联的数据未删除。");
			model.addAttribute(MsgKey.msg, list);
		}
		return "redirect:../listOrders";
	}

	@ResponseBody
	@RequestMapping(value = "listIngredientTypeJson", method = RequestMethod.POST)
	public List<IngredientType> listIngredientTypeJson(Model model) {
		List<IngredientType> list = orderService.queryIngredientType();
		return list;
	}

	@RequestMapping(value = "listIngredientType")
	public String listIngredientType(Model model) {

		List<IngredientType> list = orderService.queryIngredientType();

		model.addAttribute("list", list);

		return "order/listIngredientType";
	}

	@RequestMapping(value = "addIngredientType", method = RequestMethod.POST)
	public String addIngredientType(IngredientType type, RedirectAttributes redirectAttributes) {

		List<String> msgList = new ArrayList<String>();

		orderService.addIngredientType(type);
		msgList.add("添加成功");
		redirectAttributes.addFlashAttribute(MsgKey.msg, msgList);
		return "redirect:listIngredientType";
	}

	// http://localhost:8080/WMS/order/delIngredientType/1
	@RequestMapping(value = "delIngredientType/{id}")
	public String delIngredientType(@PathVariable int id, Model model) {
		try {
			orderService.deleteIngredientType(id);
		} catch (JpaSystemException e) {
			List<String> list = new ArrayList<>();
			list.add("删除失败，有相关联的数据未删除。");
			model.addAttribute(MsgKey.msg, list);
		}
		return "redirect:../listIngredientType";
	}

	@RequestMapping(value = "listSupplier")
	public String listSupplier(Model model) {

		List<Supplier> list = orderService.querySupplier();

		model.addAttribute("list", list);

		return "order/listSupplier";
	}
	
	@ResponseBody
	@RequestMapping(value = "listSupplierJson")
	public List<Supplier> listSupplierJson(Model model) {

		return orderService.querySupplier();
	}


	@RequestMapping(value = "addSupplier", method = RequestMethod.POST)
	public String addSupplier(Supplier type, RedirectAttributes redirectAttributes) {

		List<String> msgList = new ArrayList<String>();

		orderService.addSupplier(type);
		msgList.add("添加成功");
		redirectAttributes.addFlashAttribute(MsgKey.msg, msgList);
		return "redirect:listSupplier";
	}

	@RequestMapping(value = "delSupplier/{id}")
	public String delSupplier(@PathVariable int id) {
		orderService.deleteSupplier(id);
		return "redirect:../listSupplier";
	}

	@RequestMapping(value = "listIngredient")
	public String listIngredient(Model model) {

		List<Ingredient> list = orderService.queryIngredient();

		model.addAttribute("list", list);

		return "order/listIngredient";
	}
	
	@ResponseBody
	@RequestMapping(value = "listIngredientJson")
	public List<Ingredient> listIngredientJson(Model model) {
		return orderService.queryIngredient();
	}

	@RequestMapping(value = "addIngredient", method = RequestMethod.POST)
	public String addIngredient(Ingredient ingredient, RedirectAttributes redirectAttributes) {
		orderService.addIngredient(ingredient);
		return "redirect:listIngredient";
	}

	@RequestMapping(value = "delIngredient/{id}")
	public String delIngredient(@PathVariable int id, Model model) {
		try {
			orderService.deleteIngredient(id);
		} catch (JpaSystemException e) {
			e.printStackTrace();
			model.addAttribute(MsgKey.msg, "有相关关联数据不可删除");
		}
		return "redirect:../listIngredient";
	}

}
