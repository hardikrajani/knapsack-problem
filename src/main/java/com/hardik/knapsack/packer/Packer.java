package com.hardik.knapsack.packer;

import java.util.List;

import com.hardik.knapsack.exception.APIException;
import com.hardik.knapsack.model.ItemPackage;
import com.hardik.knapsack.utilities.FileExtractor;
import com.hardik.knapsack.utilities.PackageFinder;
import com.hardik.knapsack.utilities.ValidationUtil;

public class Packer {

	private Packer() {
	}

	public static String pack(String filePath) throws APIException {
		if(filePath==null)
			throw new APIException("FilePath cannot be null");
		List<ItemPackage> itemPackages = FileExtractor.getInstance().extractFile(filePath);
		StringBuilder solution = new StringBuilder();
		for (ItemPackage itemPackage : itemPackages) {
			if (ValidationUtil.validatePackage(itemPackage)) {
				PackageFinder finder = new PackageFinder();
				solution.append(finder.find(itemPackage) + "\n");
			}
		}
		return solution.toString().trim();
	}
}
