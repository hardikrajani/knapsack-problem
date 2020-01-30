package com.hardik.knapsack.model;

public class BaseItem {

	protected final Double weight;
	protected final Double cost;

	public BaseItem(Double weight, Double cost) {
		super();
		this.weight = weight;
		this.cost = cost;
	}

	public Double getWeight() {
		return weight;
	}

	public Double getCost() {
		return cost;
	}

	public Double getRatio() {
		return this.cost / this.weight;
	}

}
