package com.alberto.analyzer;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import com.alberto.analyzer.Basket;
import com.alberto.analyzer.DetailBasket;
import com.alberto.analyzer.PrintBasket;
import com.alberto.analyzer.config.Config;
import com.alberto.analyzer.config.ConfigBatch;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class TestPrintPrintBasket {
	
	String userDir = System.getProperty("user.dir");
	PrintBasket printBasket;
  
	@Before
	public void init() {
	    String fileConfigOk = userDir + "/config/configOk.json";
        try {
        	Config config = new ConfigBatch(fileConfigOk);  
			printBasket = new PrintBasket(config);
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
			PrintBasket printBasketError = new PrintBasket(config);
	    } catch (IOException | ParseException exception) {
			//exception.printStackTrace();
			assertTrue(true);
	    }
    }
	
    @Test 
    public void testPrintVoid() {
    	Basket testBasket = new Basket();
    	String resultPrintBasket = "• Sales Taxes: 0.00\n"
    	                         + "• Total: 0.00";

    	assertEquals(resultPrintBasket, printBasket.printBasket(testBasket));
    }
    
    @Test 
    public void testPrintOneLineWithTaxes() {
    	List<DetailBasket> listDetailBasket = Arrays.asList(new DetailBasket(1.0, "Red Wine", 9.45, 5.0));
     	Basket testBasket = new Basket(listDetailBasket);
        String resultPrintBasket = "• 1 Red Wine: 9.90\n"
        						 + "• Sales Taxes: 0.45\n"
        						 + "• Total: 9.90";
    	assertEquals(resultPrintBasket, printBasket.printBasket(testBasket));
    }
    @Test 
    public void testPrintOneLineWithoutTaxes() {
    	List<DetailBasket> listDetailBasket = Arrays.asList(new DetailBasket(1.0, "Bread", 55.02, 0.0));
     	Basket testBasket = new Basket(listDetailBasket);
        String resultPrintBasket = "• 1 Bread: 55.02\n"
        						 + "• Sales Taxes: 0.00\n"
        						 + "• Total: 55.02";
    	assertEquals(resultPrintBasket, printBasket.printBasket(testBasket));
    }
    
    @Test 
    public void testPrintTwoLines() {
    	List<DetailBasket> listDetailBasket = Arrays.asList(
    			new DetailBasket(2.0, "Red Wine", 11.23, 5.0),
    			new DetailBasket(5.0, "Bread", 55.02, 0.0));
       	Basket testBasket = new Basket(listDetailBasket);
        String resultPrintBasket = "• 2 Red Wine: 23.56\n"
        		                 + "• 5 Bread: 275.10\n"
				                 + "• Sales Taxes: 1.10\n"
				                 + "• Total: 298.66";
        assertEquals(resultPrintBasket, printBasket.printBasket(testBasket));
    }
    
    @Test 
    public void testPrintVoidLine() {
    	Basket testBasket = new Basket();
    	String[] resultPrintBasket = {"• Sales Taxes: 0.00",
    	                              "• Total: 0.00"};
    	String[] textBasket = printBasket.printBasketList(testBasket);
    	assertEquals(resultPrintBasket[0], textBasket[0]);
    	assertEquals(resultPrintBasket[1], textBasket[1]);
    }
    
    @Test 
    public void testPrintOneLineWithTaxesLine() {
    	List<DetailBasket> listDetailBasket = Arrays.asList(new DetailBasket(1.0, "Red Wine", 9.45, 5.0));
     	Basket testBasket = new Basket(listDetailBasket);
        String[] resultPrintBasket = {"• 1 Red Wine: 9.90",
        						      "• Sales Taxes: 0.45",
        						      "• Total: 9.90"};
       	String[] textBasket = printBasket.printBasketList(testBasket);
    	assertEquals(resultPrintBasket[0], textBasket[0]);
    	assertEquals(resultPrintBasket[1], textBasket[1]);
       	assertEquals(resultPrintBasket[2], textBasket[2]);
    }
    @Test 
    public void testPrintOneLineWithoutTaxesLine() {
    	List<DetailBasket> listDetailBasket = Arrays.asList(new DetailBasket(1.0, "Bread", 55.02, 0.0));
     	Basket testBasket = new Basket(listDetailBasket);
        String[] resultPrintBasket = {"• 1 Bread: 55.02",
        						      "• Sales Taxes: 0.00",
        						      "• Total: 55.02"};
      	String[] textBasket = printBasket.printBasketList(testBasket);
    	assertEquals(resultPrintBasket[0], textBasket[0]);
    	assertEquals(resultPrintBasket[1], textBasket[1]);
       	assertEquals(resultPrintBasket[2], textBasket[2]);
    }
    
    @Test 
    public void testPrintTwoLinesLine() {
    	List<DetailBasket> listDetailBasket = Arrays.asList(
    			new DetailBasket(2.0, "Red Wine", 11.23, 5.0),
    			new DetailBasket(5.0, "Bread", 55.02, 0.0));
       	Basket testBasket = new Basket(listDetailBasket);
        String[] resultPrintBasket = {"• 2 Red Wine: 23.56",
        		                      "• 5 Bread: 275.10",
				                      "• Sales Taxes: 1.10",
				                      "• Total: 298.66"};
     	String[] textBasket = printBasket.printBasketList(testBasket);
    	assertEquals(resultPrintBasket[0], textBasket[0]);
    	assertEquals(resultPrintBasket[1], textBasket[1]);
       	assertEquals(resultPrintBasket[2], textBasket[2]);
       	assertEquals(resultPrintBasket[3], textBasket[3]);
    }
}
