package com.alberto.analyzer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alberto.analyzer.ProcessCalculateTaxes;

public class TestProcessCalculateTaxes {
	
	ProcessCalculateTaxes processCalculateTaxes;
	ProcessCalculateTaxes processCalculateTaxesOnline;
	String userDir = System.getProperty("user.dir");

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void init() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));

	    String fileConfigOk = userDir + "/config/configOk.json";
		processCalculateTaxes = new ProcessCalculateTaxes(fileConfigOk);
		processCalculateTaxesOnline = new ProcessCalculateTaxes();
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(System.out);
	    System.setErr(System.err);
	}

	@Test
	public void TestErrorFileConfig() {
		String fileConfigError = userDir + "/config/configError.json";
		@SuppressWarnings("unused")
		ProcessCalculateTaxes processCalculateTaxesTest 
		          = new ProcessCalculateTaxes(fileConfigError);
		assertEquals("Error in File Configuration Json Error\n", 
				     errContent.toString());
		}

	@Test
	public void TestImput1() {
		String textImput1 = "• 1 book at 12.49\n" 
	                      + "• 1 music CD at 14.99\n" 
				          + "• 1 chocolate bar at 0.85";
		String textOutput1 = "• 1 book: 12.49\n"  
		                   + "• 1 music CD: 16.49\n"  
				           + "• 1 chocolate bar: 0.85\n"  
		                   + "• Sales Taxes: 1.50\n"  
				           + "• Total: 29.83";
		assertEquals(textOutput1, 
				     processCalculateTaxes.processTaxesBasket(textImput1));
	}

	@Test
	public void TestImput2() {
		String textImput2 = "• 1 imported box of chocolates at 10.00\n" 
	                      + "• 1 imported bottle of perfume at 47.50";
		String textOutput2 = "• 1 imported box of chocolates: 10.50\n" 
	                       + "• 1 imported bottle of perfume: 54.65\n" 
				           + "• Sales Taxes: 7.65\n"  
	                       + "• Total: 65.15";
		assertEquals(textOutput2, 
				processCalculateTaxes.processTaxesBasket(textImput2));
	}

	@Test
	//wrong in doc round(11.25*5/100)= round(0.565) = 0.55
	public void TestImput3() {
		String textImput3 = "• 1 imported bottle of perfume at 27.99\n"  
	                      + "• 1 bottle of perfume at 18.99\n"  
				          + "• 1 packet of headache pills at 9.75\n"  
	                      + "• 1 box of imported chocolates at 11.25";
		String textOutput3 = "• 1 imported bottle of perfume: 32.19\n"  
	                       + "• 1 bottle of perfume: 20.89\n"  
				           + "• 1 packet of headache pills: 9.75\n"  
	                       + "• 1 imported box of chocolates: 11.80\n" 
				           + "• Sales Taxes: 6.65\n" 
	                       + "• Total: 74.63";
		assertEquals(textOutput3, 
				processCalculateTaxes.processTaxesBasket(textImput3));
	}

	@Test
	public void TestImput1List() {
		String[] listImput1 = { "• 1 book at 12.49", 
				                "• 1 music CD at 14.99", 
				                "• 1 chocolate bar at 0.85" };
		String textOutput1 = "• 1 book: 12.49\n"  
				           + "• 1 music CD: 16.49\n"  
				           + "• 1 chocolate bar: 0.85\n"  
				           + "• Sales Taxes: 1.50\n"  
				           + "• Total: 29.83";

		assertEquals(textOutput1, 
				processCalculateTaxes.processTaxesBasket(listImput1));
	}

	@Test
	public void TestImput2List() {
		String[] listImput2 = { "• 1 imported box of chocolates at 10.00", 
				                "• 1 imported bottle of perfume at 47.50" };
		String textOutput2 = "• 1 imported box of chocolates: 10.50\n" + 
				             "• 1 imported bottle of perfume: 54.65\n" + 
				             "• Sales Taxes: 7.65\n" + 
				             "• Total: 65.15";
		assertEquals(textOutput2, 
				processCalculateTaxes.processTaxesBasket(listImput2));
	}

	@Test
	//wrong in doc round(11.25*5/100)= round(0.565) = 0.55
	public void TestImput3List() {
		String[] textImput3 = { "• 1 imported bottle of perfume at 27.99", 
				                "• 1 bottle of perfume at 18.99",
				                "• 1 packet of headache pills at 9.75", 
				                "• 1 box of imported\n chocolates at 11.25" };
		String textOutput3 = "• 1 imported bottle of perfume: 32.19\n" + 
				             "• 1 bottle of perfume: 20.89\n" + 
				             "• 1 packet of headache pills: 9.75\n" + 
				             "• 1 imported box of chocolates: 11.80\n" + 
				             "• Sales Taxes: 6.65\n" + 
				             "• Total: 74.63";
		assertEquals(textOutput3, 
				processCalculateTaxes.processTaxesBasket(textImput3));
	}

	@Test
	public void TestFileBasketErrorNonExist() {
		String fileErrorBasket = userDir + "/data/fileErrorBasket.txt";
		processCalculateTaxes.processTaxesBasketFile(fileErrorBasket);
		//the message has got path of execution Only verfy first part 
		assertEquals("Error reading File Basket\n"
				+ "Message: "
				, errContent.toString().substring(0, 35));
		assertTrue( errContent.toString().length() > 35);
		}
	
	@Test
	public void TestFilebasketErrorVoid() {
		String fileVoidBasket = userDir + "/data/fileBasketVoid.txt";
		String textVoid = "• Sales Taxes: 0.00\n"  
	                    + "• Total: 0.00";
		assertEquals(textVoid, processCalculateTaxes.processTaxesBasketFile(fileVoidBasket));
		}
	
	@Test
	public void TestImput1File() {
		String fileBasketTest1 = userDir + "/data/fileBasketImput1.txt";
		String textOutput1 = "• 1 book: 12.49\n" 
		                   + "• 1 music CD: 16.49\n" 
				           + "• 1 chocolate bar: 0.85\n"
				           + "• Sales Taxes: 1.50\n" 
				           + "• Total: 29.83";

		assertEquals(textOutput1, processCalculateTaxes.processTaxesBasketFile(fileBasketTest1));
	}

	@Test
	public void TestImput2File() {
		String fileBasketTest2 = userDir + "/data/fileBasketImput2.txt";
		String textOutput2 = "• 1 imported box of chocolates: 10.50\n" 
		                   + "• 1 imported bottle of perfume: 54.65\n" 
				           + "• Sales Taxes: 7.65\n" 
		                   + "• Total: 65.15";
		assertEquals(textOutput2, processCalculateTaxes.processTaxesBasketFile(fileBasketTest2));
	}

	@Test
	//wrong in doc round(11.25*5/100)= round(0.565) = 0.55
	public void TestImput3File() {
		String fileBasketTest3 = userDir + "/data/fileBasketImput3.txt";
		String textOutput3 = "• 1 imported bottle of perfume: 32.19\n" 
		                   + "• 1 bottle of perfume: 20.89\n"
				           + "• 1 packet of headache pills: 9.75\n" 
		                   + "• 1 imported box of chocolates: 11.80\n"
				           + "• Sales Taxes: 6.65\n" 
		                   + "• Total: 74.63";
		assertEquals(textOutput3, processCalculateTaxes.processTaxesBasketFile(fileBasketTest3));
	}

	@Test
	public void TestImput1Online() {
		String textImput1 = "• 1 book at 12.49\n" 
	                      + "• 1 music CD at 14.99\n" 
				          + "• 1 chocolate bar at 0.85";
		String textOutput1 = "• 1 book: 12.49\n"  
		                   + "• 1 music CD: 16.49\n"  
				           + "• 1 chocolate bar: 0.85\n"  
		                   + "• Sales Taxes: 1.50\n"  
				           + "• Total: 29.83";
		assertEquals(textOutput1, 
				     processCalculateTaxesOnline.processTaxesBasket(textImput1));
	}

	@Test
	public void TestImput2Online() {
		String textImput2 = "• 1 imported box of chocolates at 10.00\n" 
	                      + "• 1 imported bottle of perfume at 47.50";
		String textOutput2 = "• 1 imported box of chocolates: 10.50\n" 
	                       + "• 1 imported bottle of perfume: 54.65\n" 
				           + "• Sales Taxes: 7.65\n"  
	                       + "• Total: 65.15";
		assertEquals(textOutput2, 
				processCalculateTaxesOnline.processTaxesBasket(textImput2));
	}

	@Test
	//wrong in doc round(11.25*5/100)= round(0.565) = 0.55
	public void TestImput3Online() {
		String textImput3 = "• 1 imported bottle of perfume at 27.99\n"  
	                      + "• 1 bottle of perfume at 18.99\n"  
				          + "• 1 packet of headache pills at 9.75\n"  
	                      + "• 1 box of imported chocolates at 11.25";
		String textOutput3 = "• 1 imported bottle of perfume: 32.19\n"  
	                       + "• 1 bottle of perfume: 20.89\n"  
				           + "• 1 packet of headache pills: 9.75\n"  
	                       + "• 1 imported box of chocolates: 11.80\n" 
				           + "• Sales Taxes: 6.65\n" 
	                       + "• Total: 74.63";
		assertEquals(textOutput3, 
				processCalculateTaxesOnline.processTaxesBasket(textImput3));
	}

	@Test
	public void TestImput1ListOnline() {
		String[] listImput1 = { "• 1 book at 12.49", 
				                "• 1 music CD at 14.99", 
				                "• 1 chocolate bar at 0.85" };
		String textOutput1 = "• 1 book: 12.49\n"  
				           + "• 1 music CD: 16.49\n"  
				           + "• 1 chocolate bar: 0.85\n"  
				           + "• Sales Taxes: 1.50\n"  
				           + "• Total: 29.83";

		assertEquals(textOutput1, 
				processCalculateTaxesOnline.processTaxesBasket(listImput1));
	}

	@Test
	public void TestImput2ListOnline() {
		String[] listImput2 = { "• 1 imported box of chocolates at 10.00", 
				                "• 1 imported bottle of perfume at 47.50" };
		String textOutput2 = "• 1 imported box of chocolates: 10.50\n" + 
				             "• 1 imported bottle of perfume: 54.65\n" + 
				             "• Sales Taxes: 7.65\n" + 
				             "• Total: 65.15";
		assertEquals(textOutput2, 
				processCalculateTaxesOnline.processTaxesBasket(listImput2));
	}

	@Test
	//wrong in doc round(11.25*5/100)= round(0.565) = 0.55
	public void TestImput3ListOnline() {
		String[] textImput3 = { "• 1 imported bottle of perfume at 27.99", 
				                "• 1 bottle of perfume at 18.99",
				                "• 1 packet of headache pills at 9.75", 
				                "• 1 box of imported\n chocolates at 11.25" };
		String textOutput3 = "• 1 imported bottle of perfume: 32.19\n" + 
				             "• 1 bottle of perfume: 20.89\n" + 
				             "• 1 packet of headache pills: 9.75\n" + 
				             "• 1 imported box of chocolates: 11.80\n" + 
				             "• Sales Taxes: 6.65\n" + 
				             "• Total: 74.63";
		assertEquals(textOutput3, 
				processCalculateTaxesOnline.processTaxesBasket(textImput3));
	}

	@Test
	public void TestImput1FileOnline() {
		String fileBasketTest1 = userDir + "/data/fileBasketImput1.txt";
		String textOutput1 = "• 1 book: 12.49\n" 
		                   + "• 1 music CD: 16.49\n" 
				           + "• 1 chocolate bar: 0.85\n"
				           + "• Sales Taxes: 1.50\n" 
				           + "• Total: 29.83";

		assertEquals(textOutput1, 
				    processCalculateTaxesOnline.processTaxesBasketFile(fileBasketTest1));
	}

	@Test
	public void TestImput2FileOnline() {
		String fileBasketTest2 = userDir + "/data/fileBasketImput2.txt";
		String textOutput2 = "• 1 imported box of chocolates: 10.50\n" 
		                   + "• 1 imported bottle of perfume: 54.65\n" 
				           + "• Sales Taxes: 7.65\n" 
		                   + "• Total: 65.15";
		assertEquals(textOutput2, 
				     processCalculateTaxesOnline.processTaxesBasketFile(fileBasketTest2));
	}

	@Test
	//wrong in doc round(11.25*5/100)= round(0.565) = 0.55
	public void TestImput3FileOnline() {
		String fileBasketTest3 = userDir + "/data/fileBasketImput3.txt";
		String textOutput3 = "• 1 imported bottle of perfume: 32.19\n" 
		                   + "• 1 bottle of perfume: 20.89\n"
				           + "• 1 packet of headache pills: 9.75\n" 
		                   + "• 1 imported box of chocolates: 11.80\n"
				           + "• Sales Taxes: 6.65\n" 
		                   + "• Total: 74.63";
		assertEquals(textOutput3, 
				     processCalculateTaxesOnline.processTaxesBasketFile(fileBasketTest3));
	}

}
