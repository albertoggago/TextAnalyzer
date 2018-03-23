package com.liferay.doc.alberto.analyzer;

import org.junit.Test;

import static org.junit.Assert.*;


public class TestDetailBasket {
	@Test
	public void TestDetailBasketWithoutTaxes() {
		String textBasketResult = "Detail Basket [Amount=1.0, Name product=book, "
                + "Price product=50.88, Taxes=0.0, Final price=50.88]";

		assertEquals(textBasketResult, 
		         new DetailBasket(1.0, "book", 50.88, 0.0).toString());
	}

	@Test
	public void TestDetailBasketWithTaxes() {
		String textBasketResult = "Detail Basket [Amount=1.0, Name product=book, "
                + "Price product=50.88, Taxes=2.55, Final price=53.43]";

		assertEquals(textBasketResult, 
		         new DetailBasket(1.0, "book", 50.88, 5.0).toString());
	}

	@Test
	public void TestDetailBasketWithTaxesExtras() {
		String textBasketResult = "Detail Basket [Amount=1.0, Name product=book, "
                + "Price product=50.88, Taxes=7.65, Final price=58.53]";

		assertEquals(textBasketResult, 
		         new DetailBasket(1.0, "book", 50.88, 15.0).toString());
	}

	@Test
	public void TestDetailBasketBigNumbers() {
		String textBasketResult = "Detail Basket [Amount=13.0, Name product=Big Numbers, "
                + "Price product=100458.33, Taxes=222012.7, Final price=1527970.99]";

		assertEquals(textBasketResult, 
		         new DetailBasket(13.0, "Big Numbers", 100458.33, 17.0).toString());
	}
	@Test
	public void TestDetailBasketNoAmount() {
		String textBasketResult = "Detail Basket [Amount=0.0, Name product=No amount, "
                + "Price product=50.88, Taxes=0.0, Final price=0.0]";

		assertEquals(textBasketResult, 
		         new DetailBasket(0.0, "No amount", 50.88, 9.0).toString());
	}
	@Test
	public void TestDetailBasketNoPrice() {
		String textBasketResult = "Detail Basket [Amount=2.0, Name product=no price, "
                + "Price product=0.0, Taxes=0.0, Final price=0.0]";

		assertEquals(textBasketResult, 
		         new DetailBasket(2.0, "no price", 0.0, 5.0).toString());
	}

	@Test
	public void TestDetailBasketRedWine() {
		String textBasketResult = "Detail Basket [Amount=1.0, Name product=Red Wine, "
				+ "Price product=9.45, Taxes=0.45, Final price=9.9]";

		assertEquals(textBasketResult, 
		         new DetailBasket(1.0, "Red Wine", 9.45, 5.0).toString());
	}
}
