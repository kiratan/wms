package com.Master5.main.web.order.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Master5.main.utils.constant.MsgKey;
import com.Master5.main.web.order.entry.IngredientType;
import com.Master5.main.web.order.entry.Orders;
import com.Master5.main.web.order.entry.Supplier;
import com.Master5.main.web.order.service.OrderService;


@Controller
@RequestMapping(value = "order")
public class OrdersController {

	@Autowired
	OrderService orderService;
	

	@RequestMapping(value = { "", "list" })
	public String listOrder(Model model) {

		List<Orders> list = orderService.query();

		model.addAttribute("list", list);

		return "order/list";
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

	//http://localhost:8080/WMS/order/delIngredientType/1
	@RequestMapping(value = "delIngredientType/{id}")
	public String delIngredientType(@PathVariable int id) {
		orderService.deleteIngredientType(id);
		return "redirect:../listIngredientType";
	}
	
	@RequestMapping(value = "listSupplier")
	public String listSupplier(Model model) {

		List<Supplier> list = orderService.querySupplier();

		model.addAttribute("list", list);

		return "order/listSupplier";
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

}
