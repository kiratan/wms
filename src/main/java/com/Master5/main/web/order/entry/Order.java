package com.Master5.main.web.order.entry;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.Table;

import com.Master5.main.web.user.entry.User;

/**
 * 订单
 * 
 * @author Bada Lee
 *
 */
@Entity
@Table(name = "order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int supplierID;

	private int status;

	private String remarks;

	private Date createtime;

	@JoinColumn(name="buyyerID")
	private User buyyer;

	private Date buttime;

	@JoinColumn(name="managerID")
	private User manager;

	private Date intime;

	@ManyToMany
	@JoinTable(name = "order_ingredient", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "ingredient_id")})
	private List<Ingredient> ingredients;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public User getBuyyer() {
		return buyyer;
	}

	public void setBuyyer(User buyyer) {
		this.buyyer = buyyer;
	}

	public Date getButtime() {
		return buttime;
	}

	public void setButtime(Date buttime) {
		this.buttime = buttime;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	

}
