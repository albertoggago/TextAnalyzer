package com.alberto.analyzer;

import org.junit.Test;

import com.alberto.analyzer.AnalyzeDetailBasket;
import com.alberto.analyzer.ReadBasket;
import com.alberto.analyzer.config.Config;
import com.alberto.analyzer.config.ConfigBatch;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Before;

public class TestReadBasket {

	String userDir = System.getProperty("user.dir");
	AnalyzeDetailBasket analyzeDetailBasket;
	ReadBasket readBasket;
	
	@Before
	public void init() {
	    String fileConfigOk = userDir + "/config/configOk.json";
        try {
			Config config = new ConfigBatch(fileConfigOk);
			analyzeDetailBasket = new AnalyzeDetailBasket(config);
			readBasket = new ReadBasket(config);
	    } catch (IOException | ParseException exception) {
			exception.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void TestReadBasketString() {
		String textbasketString = "• 1 book at 12.49\n"
	                            + "• 1 music CD at 14.99\n" 
				                + "• 1 chocolate bar at 0.85";
		String textBasketResult = "Basket [SalesTaxes=1.5, Total=29.83, "
				+ "DetailsBasket=["
				+ "[Detail Basket [Amount=1.0, Name product=book, Price product=12.49, "
				+ "Taxes=0.0, Final price=12.49], "
				+ "Detail Basket [Amount=1.0, Name product=music CD, Price product=14.99, "
				+ "Taxes=1.5, Final price=16.49], "
				+ "Detail Basket [Amount=1.0, Name product=chocolate bar, Price product=0.85, "
				+ "Taxes=0.0, Final price=0.85]]]";

		assertEquals(textBasketResult, readBasket.readBasket(textbasketString).toString());
	}

	@Test
	public void TestReadBasketList() {
		String[] textbasketList = {"• 1 imported box of chocolates at 10.00", 
				                   "• 1 imported bottle of perfume at 47.50"};
		String textBasketResult = "Basket [SalesTaxes=7.65, Total=65.15, "
				+ "DetailsBasket=["
				+ "[Detail Basket [Amount=1.0, Name product=imported box of chocolates, "
				+ "Price product=10.0, Taxes=0.5, Final price=10.5], "
				+ "Detail Basket [Amount=1.0, Name product=imported bottle of perfume, "
				+ "Price product=47.5, Taxes=7.15, Final price=54.65]]]";
		assertEquals(textBasketResult, readBasket.readBasket(textbasketList).toString());
	}

	@Test
	//wrong in doc round(11.25*5/100)= round(0.565) = 0.55
	public void TestBasketFile() {
		String fileBasketTest= userDir + "/data/fileBasketImput3.txt";
		String textBasketResult = "Basket [SalesTaxes=6.65, Total=74.63, "
				+ "DetailsBasket=["
				+ "[Detail Basket [Amount=1.0, Name product=imported bottle of perfume, "
				+ "Price product=27.99, Taxes=4.2, Final price=32.19], "
				+ "Detail Basket [Amount=1.0, Name product=bottle of perfume, "
				+ "Price product=18.99, Taxes=1.9, Final price=20.89], "
				+ "Detail Basket [Amount=1.0, Name product=packet of headache pills, "
				+ "Price product=9.75, Taxes=0.0, Final price=9.75], "
				+ "Detail Basket [Amount=1.0, Name product=imported box of chocolates, "
				+ "Price product=11.25, Taxes=0.55, Final price=11.8]]]";
		try {
			assertEquals(textBasketResult, readBasket.readBasketFile(fileBasketTest).toString());
		} catch (IOException exception) {
			exception.printStackTrace();
			assertTrue(false);
		}
	}
	@Test

	//wrong in doc round(11.25*5/100)= round(0.565) = 0.55
	public void TestBasketErrorRound() {
		String[] textbasketList = {"• 1 imported box of chocolates at 11.25"};
		String textBasketResult = "Basket [SalesTaxes=0.55, Total=11.8, "
				+ "DetailsBasket=["
				+ "[Detail Basket [Amount=1.0, Name product=imported box of chocolates, "
				+ "Price product=11.25, Taxes=0.55, Final price=11.8]]]";
		assertEquals(textBasketResult, readBasket.readBasket(textbasketList).toString());
	}
}
