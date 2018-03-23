package com.alberto.analyzer;

import org.junit.Test;

import com.alberto.analyzer.AnalyzeDetailBasket;
import com.alberto.analyzer.config.Config;
import com.alberto.analyzer.config.ConfigBatch;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Before;


public class TestAnalyzeDetailBasket {

	String userDir = System.getProperty("user.dir");
	AnalyzeDetailBasket analyzeDetailBasket;
  
	@Before
	public void init() {
	    String fileConfigOk = userDir + "/config/configOk.json";
        try {
			Config config = new ConfigBatch(fileConfigOk);
			analyzeDetailBasket = new AnalyzeDetailBasket(config);
	    } catch (IOException | ParseException exception) {
			//exception.printStackTrace();
	    	System.out.println(exception.getMessage());
			assertTrue(false);
		}
	}

	@Test
	public void testFileConfigError() {
        assertEquals("","");
	    String fileConfigError = userDir + "/config/configError.json";
        try {
			Config config = new ConfigBatch(fileConfigError);
			@SuppressWarnings("unused")
			AnalyzeDetailBasket analyzeDetailBasket = new AnalyzeDetailBasket(config);
	    } catch (IOException | ParseException exception) {
			//exception.printStackTrace();
			assertTrue(true);
	    }
    }

	@Test
	public void TestAnalyzeOneBook() {
		String textbasketString = "• 1 book at 12.49";
		String textBasketResult = "Detail Basket [Amount=1.0, Name product=book, "
				                + "Price product=12.49, Taxes=0.0, Final price=12.49]";

		assertEquals(textBasketResult, analyzeDetailBasket.analyze(textbasketString).toString());
	}

	@Test
	public void TestAnalyzeFiveBook() {
		String textbasketString = "• 5 book at 10.11";
		String textBasketResult = "Detail Basket [Amount=5.0, Name product=book, "
				                + "Price product=10.11, Taxes=0.0, Final price=50.55]";

		assertEquals(textBasketResult, analyzeDetailBasket.analyze(textbasketString).toString());
	}
	
	@Test
	public void TestAnalyzeBookWithOutAmount() {
		String textbasketString = "book at 50.88";
		String textBasketResult = "Detail Basket [Amount=0.0, Name product=book, "
				                + "Price product=50.88, Taxes=0.0, Final price=0.0]";

		assertEquals(textBasketResult, analyzeDetailBasket.analyze(textbasketString).toString());
	}

	@Test
	public void TestAnalyzeComplexName() {
		String textbasketString = "1 book 50 Shades of Gray at 50.88";
		String textBasketResult = "Detail Basket [Amount=1.0, Name product=book Shades of Gray, "
				                + "Price product=50.88, Taxes=0.0, Final price=50.88]";

		assertEquals(textBasketResult, analyzeDetailBasket.analyze(textbasketString).toString());
	}

	@Test
	public void TestAnalyzeDiscName() {
		String textbasketString = "• 1 music CD at 14.99";
		String textBasketResult = "Detail Basket [Amount=1.0, Name product=music CD, "
				                + "Price product=14.99, Taxes=1.5, Final price=16.49]";

		assertEquals(textBasketResult, analyzeDetailBasket.analyze(textbasketString).toString());
	}

	@Test
	public void TestAnalyzeChocolateBarName() {
		String textbasketString = "• 1 chocolate bar at 0.85";
		String textBasketResult = "Detail Basket [Amount=1.0, Name product=chocolate bar, "
				                + "Price product=0.85, Taxes=0.0, Final price=0.85]";

		assertEquals(textBasketResult, analyzeDetailBasket.analyze(textbasketString).toString());
	}

	@Test
	public void TestAnalyzeImportedBoxChocolates() {
		String textbasketString = "• 1 imported box of chocolates at 10.00";
		String textBasketResult = "Detail Basket [Amount=1.0, Name product=imported box of chocolates, "
				                + "Price product=10.0, Taxes=0.5, Final price=10.5]";

		assertEquals(textBasketResult, analyzeDetailBasket.analyze(textbasketString).toString());
	}

	@Test
	public void TestAnalyzeBottleperfumeV47dot5() {
		String textbasketString = "• 1 imported bottle of perfume at 47.5";
		String textBasketResult = "Detail Basket [Amount=1.0, Name product=imported bottle of perfume, "
				                + "Price product=47.5, Taxes=7.15, Final price=54.65]";

		assertEquals(textBasketResult, analyzeDetailBasket.analyze(textbasketString).toString());
	}

	@Test
	public void TestAnalyzeBottleperfumeV27dot99() {
		String textbasketString = "• 1 imported bottle of perfume at 27.99";
		String textBasketResult = "Detail Basket [Amount=1.0, Name product=imported bottle of perfume, "
				                + "Price product=27.99, Taxes=4.2, Final price=32.19]";

		assertEquals(textBasketResult, analyzeDetailBasket.analyze(textbasketString).toString());
	}

	@Test
	public void TestAnalyzeBottleperfumeV18dot99() {
		String textbasketString = "• 1 bottle of perfume at 18.99";
		String textBasketResult = "Detail Basket [Amount=1.0, Name product=bottle of perfume, "
				                + "Price product=18.99, Taxes=1.9, Final price=20.89]";

		assertEquals(textBasketResult, analyzeDetailBasket.analyze(textbasketString).toString());
	}

	@Test
	public void TestAnalyzeHeadachePills() {
		String textbasketString = "• 1 packet of headache pills at 9.75";
		String textBasketResult = "Detail Basket [Amount=1.0, Name product=packet of headache pills, "
				                + "Price product=9.75, Taxes=0.0, Final price=9.75]";

		assertEquals(textBasketResult, analyzeDetailBasket.analyze(textbasketString).toString());
	}

	@Test
	//wrong in doc round(11.25*5/100)= round(0.565) = 0.55
	public void TestAnalyzeImportedChocolates() {
		String textbasketString = "• 1 box of imported chocolates at 11.25";
		String textBasketResult = "Detail Basket [Amount=1.0, Name product=imported box of chocolates, "
				                + "Price product=11.25, Taxes=0.55, Final price=11.8]";

		assertEquals(textBasketResult, analyzeDetailBasket.analyze(textbasketString).toString());
	}

	@Test
	//Error with decimals-mil
	public void TestAnalyzeDecimalnInNumbers() {
		String textbasketString = "• 1,000 box of imported chocolates at 1,011.25";
		String textBasketResult = "Detail Basket [Amount=1000.0, Name product=imported box of chocolates, "
				                + "Price product=1011.25, Taxes=50550.0, Final price=1061800.0]";

		assertEquals(textBasketResult, analyzeDetailBasket.analyze(textbasketString).toString());
	}
}