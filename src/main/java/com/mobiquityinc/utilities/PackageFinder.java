package com.hardik.knapsack.utilities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.hardik.knapsack.constants.Constants;
import com.hardik.knapsack.model.Item;
import com.hardik.knapsack.model.ItemPackage;
import com.hardik.knapsack.model.MergeItems;

public class PackageFinder {

	private int packageMaxWeight;
	
	private void initilize(ItemPackage itemPackage) {
		packageMaxWeight = itemPackage.getMaxCapacity();
	}
	
	public String find(ItemPackage itemPackage) {
		initilize(itemPackage);
		return buildFinalPackage(itemPackage);
	}

	private String buildFinalPackage(ItemPackage itemPackage) {

		itemPackage.getItems().sort(Comparator.comparing(Item::getRatio).reversed());
		itemPackage.getItems().removeIf(t -> t.getWeight() > packageMaxWeight);
		
		List<MergeItems> mergeList = new ArrayList<>();
		for(int i=0; i < itemPackage.getItems().size(); i++) {
			Item item = itemPackage.getItems().get(i);
			if(!mergeList.isEmpty()) {
				List<MergeItems> newPossibleItems = new ArrayList<>();
				for (MergeItems mergeItems : mergeList) {
					double mergeCost = mergeItems.getCost() + item.getCost();
					double mergeWeight = mergeItems.getWeight() + item.getWeight();
					List<Integer> ids = new ArrayList<>();
					ids.addAll(mergeItems.getId());
					ids.add(item.getId());
					
					if(mergeWeight < packageMaxWeight) {
						newPossibleItems.add(new MergeItems(ids, mergeWeight, mergeCost));
					}
				}
				
				mergeList.addAll(newPossibleItems);
			}
			List<Integer> idList = new ArrayList<>();
			idList.add(item.getId());
			MergeItems defaultItem = new MergeItems(idList, item.getWeight(), item.getCost());
			mergeList.add(defaultItem);
		}
		mergeList.sort(Comparator.comparing(MergeItems::getCost).reversed().thenComparing(MergeItems::getWeight));
		
		if(mergeList.isEmpty()) {
			return "-";
		}
		mergeList.get(Constants.FIRST_ELEMENT).getId().sort(Comparator.naturalOrder());
		return mergeList.get(Constants.FIRST_ELEMENT).getId().stream()
		        .map(String::valueOf)
		        .collect(Collectors.joining(","));
	}
	
}
