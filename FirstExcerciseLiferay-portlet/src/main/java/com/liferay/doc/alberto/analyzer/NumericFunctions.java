package com.liferay.doc.alberto.analyzer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.Locale;

import com.liferay.doc.alberto.analyzer.config.Config;

public class NumericFunctions {

	private Config config;
	private static DecimalFormatSymbols symbols 
	           = new DecimalFormatSymbols(Locale.ENGLISH);
	
	private static int floatDecimals = 2;
    private static BigDecimal delimiter = new BigDecimal("0.05");
	private static RoundingMode roundingMode = RoundingMode.HALF_DOWN;

	public NumericFunctions (Config config){
		this.config = config;
	}
	
	protected static double roundTax(double number) {
		//different type of round using stops different to 10^(-n)
		BigDecimal divide =   (new BigDecimal(number))
				                   .divide(delimiter)
				                   .setScale(1,roundingMode);
		BigDecimal scale = divide.setScale(0,RoundingMode.HALF_UP);
		BigDecimal multiply = scale.multiply(delimiter)
				                    .setScale(floatDecimals,
				                    		  roundingMode);
	    return multiply.doubleValue();
	}

	protected static double round(double num) {
		return (new BigDecimal(num))
                .setScale(floatDecimals,roundingMode)
			.doubleValue();
	}

	public String formatFloat(double floatNumber) {
		DecimalFormat decimalFormatFloat 
		    = new DecimalFormat("###,##0."+ 
		      String.join("", 
		    		      Collections.nCopies(config.getParamFloatDecimals(),"0")), 
			                  symbols);
		return decimalFormatFloat.format(floatNumber);
	}

	public String formatInteger(double number) {
		DecimalFormat decimalFormatInteger 
		      = new DecimalFormat("###,##0", symbols);
		return decimalFormatInteger.format(number);
	}

}
