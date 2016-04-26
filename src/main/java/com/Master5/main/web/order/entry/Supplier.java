package com.Master5.main.web.order.entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 供应商
 * 
 * @author Bada Lee
 *
 */
@Entity
@Table(name = "ingredient", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(unique = true)
	private String name;

	private String address;

	private String number;
}
