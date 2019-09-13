package io.foodbankproject.foodbankapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {

	@Id
	@Column
	private Integer itemId;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private boolean perishable;

	public Item() {
		// empty constructor
	}
	
	public Item(Integer itemId, String name, String description, boolean perishable) {
		this.itemId = itemId;
		this.name = name;
		this.description = description;
		this.perishable = perishable;
	}
	
	
}
