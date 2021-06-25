package com.example.demo.beans;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemNo;
	@Column(nullable = false, unique = true)
	private String title;
	@Column(nullable = false)
	private int amount;
	@Column (nullable = false, unique = true)
	private String inventoryCode;
	
	
	public Product(int itemNo,String title, int amount, String inventoryCode) {
		super();
		this.itemNo = itemNo;
		this.title = title;
		this.amount = amount;
		this.inventoryCode = inventoryCode;
	}

	public Product() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	public int getId() {
		return itemNo;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			Product p = (Product) obj;
			if (p.getId() == this.itemNo) {
				return true;
			}
		}
		return false;
	}
	
	// if you override equals you have to override hashcode. they are linked
	@Override
	public int hashCode() {
		return (int) itemNo + title.hashCode();
	}

}
