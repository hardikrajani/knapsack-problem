package com.hardik.knapsack.model;

import java.util.List;

public class MergeItems extends BaseItem {

	private final List<Integer> id;
	
	public MergeItems(List<Integer> id, Double weight, Double cost) {
		super(weight, cost);
		this.id = id;
	}

	public List<Integer> getId() {
		return id;
	}

}
