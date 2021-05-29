package com.ejemplo.Ivan_Azul.Entities;

import java.sql.Timestamp;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"id", 
    		         "order_id", 
    	     	     "product_id", 
    		         "quantity", 
                     "unit_price", 
                     "discount", 
                     "status_id", 
                     "jobTitle", 
                     "date_allocated", 
                     "purchase_order_id", 
                     "inventory_id"})

public class Order_Details {
	
	private Integer id;
	private Integer order_id;
	private Integer product_id;
	private Float quantity;
	private Float unit_price;
	private Float discount;
	private Integer status_id;
	private Timestamp date_allocated;
	private Integer purchase_order_id;
	private Integer inventory_id;
	
	public Order_Details() {

	}
	
	public Order_Details(Integer id, Integer order_id, Integer product_id, Float quantity, Float unit_price, Float discount,
			Integer status_id, Timestamp date_allocated, Integer purchase_order_id, Integer inventory_id) {

		this.id = id;
		this.order_id = order_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.unit_price = unit_price;
		this.discount = discount;
		this.status_id = status_id;
		this.date_allocated = date_allocated;
		this.purchase_order_id = purchase_order_id;
		this.inventory_id = inventory_id;
		

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Float getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Float unit_price) {
		this.unit_price = unit_price;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Integer getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}

	public Timestamp getDate_allocated() {
		return date_allocated;
	}

	public void setDate_allocated(Timestamp date_allocated) {
		this.date_allocated = date_allocated;
	}

	public Integer getPurchase_order_id() {
		return purchase_order_id;
	}

	public void setPurchase_order_id(Integer purchase_order_id) {
		this.purchase_order_id = purchase_order_id;
	}

	public Integer getInventory_id() {
		return inventory_id;
	}

	public void setInventory_id(Integer inventory_id) {
		this.inventory_id = inventory_id;
	}
}


