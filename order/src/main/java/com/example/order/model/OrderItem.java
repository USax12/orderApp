package com.example.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@JoinColumn(name = "menu_item_id", nullable = false)
	private Long menuItemId;

	private int quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getMenuItem() {
		return menuItemId;
	}

	public void setMenuItem(Long menuItem) {
		this.menuItemId = menuItem;
	}

	public OrderItem() {
		super();

	}

	public OrderItem(Long id, Order order, Long menuItemId, int quantity) {
		super();
		this.id = id;
		this.order = order;
		this.menuItemId = menuItemId;
		this.quantity = quantity;
	}

}