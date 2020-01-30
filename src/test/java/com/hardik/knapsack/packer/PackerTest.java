package com.hardik.knapsack.packer;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.junit.jupiter.api.Test;

import com.hardik.knapsack.exception.APIException;

public class PackerTest {

	private File onePackageFile;
	private File multiplePackageFile;
	private File twoPackageOneItemFile;
	private File invalidMaxWeight, invalidMaxItems;

	@Test
	public void testWhenFilePathNotGiven() {

		assertThrows(APIException.class, () -> Packer.pack(null));
	}
	
	@Test
	public void testWhenWeightExceedsLimits() {
		invalidMaxWeight = new File(getClass().getClassLoader().getResource("invalid_max_weight.txt").getFile());

		assertThrows(APIException.class, () -> Packer.pack(URLDecoder.decode(invalidMaxWeight.getAbsolutePath(), "UTF-8")));
	}
	
	@Test
	public void testWhenItemsExceedsLimits() {
		invalidMaxItems = new File(getClass().getClassLoader().getResource("invalid_max_items_length.txt").getFile());

		assertThrows(APIException.class, () -> Packer.pack(URLDecoder.decode(invalidMaxItems.getAbsolutePath(), "UTF-8")));
	}

	@Test
	public void testWhenInvalidFilePathPassed() {
		String expectedExceptionMessage = "cannot find the path specified";

		APIException exception = assertThrows(APIException.class, () -> Packer.pack("/not/available/input.file"));
		assertTrue(exception.getMessage().contains(expectedExceptionMessage));

	}

	@Test
	public void testWhenValidOnePackageFilePassed() throws APIException, UnsupportedEncodingException {
		String expectedResult = "2,7";
		onePackageFile = new File(getClass().getClassLoader().getResource("only_one_package.txt").getFile());

		String result = Packer.pack(URLDecoder.decode(onePackageFile.getAbsolutePath(), "UTF-8"));

		assertEquals(expectedResult, result);
	}

	@Test
	public void testWhenMultiplePackageFilePassed() throws APIException, UnsupportedEncodingException {
		String expectedResult = "4\n" + 
				"-\n" + 
				"2,7\n" + 
				"8,9";
		multiplePackageFile = new File(getClass().getClassLoader().getResource("multiple_package.txt").getFile());

		String result = Packer.pack(URLDecoder.decode(multiplePackageFile.getAbsolutePath(), "UTF-8"));

		assertEquals(expectedResult, result);
	}

	@Test
	public void testWhenValidTwoPackagesWithOneItemFilePassed() throws APIException, UnsupportedEncodingException {
		String expectedResult = new StringBuilder().append("1").append("\n").append("-").toString();
		twoPackageOneItemFile = new File(
				PackerTest.class.getClassLoader().getResource("two_package_one_item.txt").getFile());

		String result = Packer.pack(URLDecoder.decode(twoPackageOneItemFile.getAbsolutePath(), "UTF-8"));

		assertEquals(expectedResult, result);
	}

}
