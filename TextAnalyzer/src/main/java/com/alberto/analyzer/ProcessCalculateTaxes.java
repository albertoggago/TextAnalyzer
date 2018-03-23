
package com.alberto.analyzer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.parser.ParseException;

import com.alberto.analyzer.config.Config;
import com.alberto.analyzer.config.ConfigBatch;
import com.alberto.analyzer.config.ConfigLiferay;

public class ProcessCalculateTaxes {
	
	private ReadBasket readBasket;
	private PrintBasket printBasket;
	
	public ProcessCalculateTaxes(){
			Config config = new ConfigLiferay(); 
			readBasket = new ReadBasket(config);
			printBasket = new PrintBasket(config);
	}

	public ProcessCalculateTaxes(String fileConfig){
		
		try {
			Config config = new ConfigBatch(fileConfig);
			readBasket = new ReadBasket(config);
			printBasket = new PrintBasket(config);
		} catch (IOException  exception) {
			System.err.println("Error reading File Basket");
			System.err.println("Message: "+exception.getMessage());
		} catch ( ParseException exception) {
			System.err.println("Error in File Configuration Json Error");
		} 
}


	public String processTaxesBasket(String textBasketImput) {
		Basket basket = this.readBasket.readBasket(textBasketImput);
		return processAfterRead(basket);
	} 
 
	public String processTaxesBasket(String[] textBasketImputList) {
		Basket basket = this.readBasket.readBasket(textBasketImputList);
		return processAfterRead(basket);
	}

	public String processTaxesBasketFile(String textBasketFile) {
		Basket basket;
		try {
			basket = this.readBasket.readBasketFile(textBasketFile);
		} catch (IOException exception) {
			basket = new Basket();
			System.err.println("Error reading File Basket");
			System.err.println("Message: "+exception.getMessage());
		}
		return processAfterRead(basket);
	}

	private String processAfterRead(Basket basket) {
		return printBasket.printBasket(basket);
	}

	public String[] processTaxesBasketList(String textBasketImput) {
		Basket basket = this.readBasket.readBasket(textBasketImput);
		return processAfterReadList(basket);
	}

	public String[] processTaxesBasketList(String[] textBasketImputList) {
		Basket basket = this.readBasket.readBasket(textBasketImputList);
		return processAfterReadList(basket);
	}
	
	public String[] processTaxesBasketFileList(String textBasketFile) {
		Basket basket;
		try {
			basket = this.readBasket.readBasketFile(textBasketFile);
		} catch (IOException exception) {
			basket = new Basket();
			System.err.println("Error reading File Basket");
			System.err.println("Message: "+exception.getMessage());
		}
		return processAfterReadList(basket);
	}


	protected String[] processAfterReadList(Basket basket) {
		return printBasket.printBasketList(basket);
	}

	public void print(String[] result, String file) {
		try (PrintWriter out = new PrintWriter(file)) {
			for (String line : result)
		    out.println(line);
		} catch (FileNotFoundException exception) {
			System.err.println("Error writing File Basket");
			System.err.println("Message: "+exception.getMessage());
		}
	}
}
