package com.liferay.doc.alberto.analyzer;

import org.junit.Test;

import com.liferay.doc.alberto.analyzer.config.Config;
import com.liferay.doc.alberto.analyzer.config.ConfigLiferay;

import static org.junit.Assert.*;

import org.junit.Before;

public class TestReadBasket {

	String userDir = System.getProperty("user.dir");
	AnalyzeDetailBasket analyzeDetailBasket;
	ReadBasket readBasket;
	
	@Before
	public void init() {
			Config config = new ConfigLiferay();
			analyzeDetailBasket = new AnalyzeDetailBasket(config);
			readBasket = new ReadBasket(config);
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
	public void TestBasketErrorRound() {
		String[] textbasketList = {"• 1 imported box of chocolates at 11.25"};
		String textBasketResult = "Basket [SalesTaxes=0.55, Total=11.8, "
				+ "DetailsBasket=["
				+ "[Detail Basket [Amount=1.0, Name product=imported box of chocolates, "
				+ "Price product=11.25, Taxes=0.55, Final price=11.8]]]";
		assertEquals(textBasketResult, readBasket.readBasket(textbasketList).toString());
	}
}
