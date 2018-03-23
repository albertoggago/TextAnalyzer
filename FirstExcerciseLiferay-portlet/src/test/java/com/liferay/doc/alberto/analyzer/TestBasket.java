package com.liferay.doc.alberto.analyzer;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;


public class TestBasket {
    @Test public void testLoadVoid() {
    	String textBasketResult = "Basket [SalesTaxes=0.0, Total=0.0, DetailsBasket=[[]]";

    	assertEquals(textBasketResult, new Basket().toString());
    }
    
    @Test public void testLoadOneLineWithTaxes() {
    	List<DetailBasket> listDetailbasket = Arrays.asList(new DetailBasket(1.0, "Red Wine", 9.45, 5.0));
    	String textBasketResult = "Basket [SalesTaxes=0.45, Total=9.9, "
    			+ "DetailsBasket=["
    			+ "[Detail Basket [Amount=1.0, Name product=Red Wine, Price product=9.45, "
    			+ "Taxes=0.45, Final price=9.9]]"
    			+ "]";

    	assertEquals(textBasketResult, new Basket(listDetailbasket).toString());
    }
    @Test public void testLoadOneLineWithoutTaxes() {
    	List<DetailBasket> listDetailbasket = Arrays.asList(new DetailBasket(1.0, "Bread", 55.02, 0.0));
    	String textBasketResult = "Basket [SalesTaxes=0.0, Total=55.02, "
    			+ "DetailsBasket=["
    			+ "[Detail Basket [Amount=1.0, Name product=Bread, Price product=55.02, "
    			+ "Taxes=0.0, Final price=55.02]]"
    			+ "]";

    	assertEquals(textBasketResult, new Basket(listDetailbasket).toString());
    }
    @Test public void testLoadTwoLines() {
    	List<DetailBasket> listDetailbasket = Arrays.asList(
    			new DetailBasket(2.0, "Red Wine", 11.23, 5.0),
    			new DetailBasket(5.0, "Bread", 55.02, 0.0));
    	String textBasketResult = "Basket [SalesTaxes=1.1, Total=298.66, "
    			+ "DetailsBasket=["
    			+ "[Detail Basket [Amount=2.0, Name product=Red Wine, Price product=11.23, "
    			+ "Taxes=1.1, Final price=23.56], "
    			+ "Detail Basket [Amount=5.0, Name product=Bread, Price product=55.02, "
    			+ "Taxes=0.0, Final price=275.1]]"
    			+ "]";

    	assertEquals(textBasketResult, new Basket(listDetailbasket).toString());
    }
}
