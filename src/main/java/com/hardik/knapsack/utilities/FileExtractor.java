package com.hardik.knapsack.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hardik.knapsack.exception.APIException;
import com.hardik.knapsack.model.Item;
import com.hardik.knapsack.model.ItemPackage;

public class FileExtractor {
	
	  private static FileExtractor instance; 
	  
	  private FileExtractor() { 
	  } 
	  
	  public static FileExtractor getInstance()  
	  { 
	    if (instance == null) { 
	      instance = new FileExtractor(); 
	    } 
	    return instance; 
	  } 

	public List<ItemPackage> extractFile(final String filePath) throws APIException {
        File file = new File(filePath);
        
        String line = "";
        List<ItemPackage> packages = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while ((line = in.readLine()) != null) {
                String[] sub1 = line.split(" : ");
                int maxWeight = Integer.parseInt(sub1[0]);
                ItemPackage itemPackage = new ItemPackage(maxWeight);
                String[] sub2 = sub1[1].split(" ");
                itemPackage.setItems(parserThings(sub2));
                packages.add(itemPackage);
            }
        } catch (IOException ioe) {
        	throw new APIException(ioe.getMessage(), ioe);
        }
        return packages;
	}
	
    public static List<Item> parserThings(String[] sub2) {
    	List<Item> items = new ArrayList<>();
        for (int i = 0; i < sub2.length; i++) {
            String[] sub3 = (sub2[i].substring(1, sub2[i].length() - 1)).split(",");
            int id = Integer.parseInt(sub3[0]);
            double weight = Double.parseDouble(sub3[1]);
            double cost = Double.parseDouble(sub3[2].substring(1, sub3[2].length()));
                Item item = new Item(id, weight, cost);
                items.add(item);
        }
        return items;
    }
}
