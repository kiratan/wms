package com.Master5.main.web.order.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Master5.main.utils.constant.MsgKey;
import com.Master5.main.web.order.entry.IngredientType;
import com.Master5.main.web.order.entry.Orders;
import com.Master5.main.web.order.service.IOrderService;

@Controller
@RequestMapping(value = "order")
public class OrdersController {

	@Autowired
	IOrderService orderService;

	@RequestMapping(value = "list")
	public String listIngredientType(Model model) {

		List<IngredientType> list = orderService.queryIngredientType();

		model.addAttribute("list", list);

		return "order/list";
	}

	@RequestMapping(value = "addIngredientType", method = RequestMethod.POST)
	public String addIngredientType(IngredientType type,RedirectAttributes redirectAttributes) {
		orderService.addIngredientType(type);
		List<String> msgList = new ArrayList<String>();
		msgList.add("添加成功");
		redirectAttributes.addFlashAttribute(MsgKey.msg, msgList);
		return "redirect:list";
	}

}
