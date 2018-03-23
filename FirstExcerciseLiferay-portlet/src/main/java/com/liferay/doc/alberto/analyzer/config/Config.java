package com.liferay.doc.alberto.analyzer.config;

import java.util.ArrayList;
import java.util.List;

public abstract class Config {

	protected static double paramBasicTaxRate;
	protected static List<String> paramTokensZeroBasicTaxRate;
	protected static double paramAdditionalTaxRate;
	protected static List<String> paramTokensAdditionalTaxRate;
	protected static List<String> paramIgnoreTokens;
	protected static List<Character> paramIgnoreChar;

	protected static String paramInitPrintText;
	protected static String paramEndPrintText;
	protected static String paramMainBodySalesTaxes;
	protected static String paramMainBodyTotal;
	
	protected static int paramFloatDecimals;
	
	protected Config()  {

		paramBasicTaxRate = 0;
		paramTokensZeroBasicTaxRate = new ArrayList<>();
		paramAdditionalTaxRate = 0;
		paramTokensAdditionalTaxRate = new ArrayList<>();
		paramIgnoreChar = new ArrayList<>();
		paramIgnoreTokens = new ArrayList<>();
		
		paramInitPrintText =  "";
		paramEndPrintText = "";
		paramMainBodySalesTaxes = "Salex Taxes";
		paramMainBodyTotal = "BodyTotal";
		
		paramFloatDecimals = 2;

	}
	
	public double getParamBasicTaxRate() {return paramBasicTaxRate;}
	public List<String> getParamTokensZeroBasicTaxRate() {
		return paramTokensZeroBasicTaxRate;}
	public double getParamAdditionalTaxRate() {return paramAdditionalTaxRate;}
	public List<String> getParamTokensAdditionalTaxRate() {
		return paramTokensAdditionalTaxRate;}
	public List<String> getParamIgnoreTokens() {return paramIgnoreTokens;}
	public List<Character> getParamIgnoreChar() {return paramIgnoreChar;}

	public String getParamInitPrintText() {return paramInitPrintText;}
	public String getParamEndPrintText() {return paramEndPrintText;}
	public String getParamMainBodySalesTaxes() {
		return paramMainBodySalesTaxes;}
	public String getParamMainBodyTotal() {
		return paramMainBodyTotal;
	}

	public int getParamFloatDecimals() {return paramFloatDecimals;}

}
