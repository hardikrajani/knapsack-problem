package com.hardik.knapsack.model;

import java.util.ArrayList;
import java.util.List;

public class ItemPackage {

	private List<Item> items;
	private int maxCapacity;

	public ItemPackage(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		items = new ArrayList<>();
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

}
