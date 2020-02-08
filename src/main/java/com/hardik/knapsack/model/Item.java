package com.hardik.knapsack.model;

public class Item extends BaseItem {
	private final Integer id;

	public Item(Integer id, Double weight, Double cost) {
		super(weight, cost);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

}
