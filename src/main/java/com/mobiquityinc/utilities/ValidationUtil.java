package com.hardik.knapsack.utilities;

import com.hardik.knapsack.constants.Constants;
import com.hardik.knapsack.exception.APIException;
import com.hardik.knapsack.model.ItemPackage;

public class ValidationUtil {

	private ValidationUtil() throws IllegalAccessException {
		throw new IllegalAccessException("Utility class");
	}

	public static boolean validatePackage(ItemPackage itemPackage) throws APIException {
		if (itemPackage.getMaxCapacity() > Constants.MAX_WEIGHT)
			throw new APIException("maximum capacity can not be more than 100.");
		if (itemPackage.getItems() != null && itemPackage.getItems().size() > Constants.MAX_ITEMS)
			throw new APIException("package can not have more than 15 items.");

		return true;
	}
}
