package com.alberto.analyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alberto.analyzer.config.Config;

public class AnalyzeDetailBasket {

	private Config config;
	
	protected AnalyzeDetailBasket (Config config)  {
		this.config = config;  
	}

	protected DetailBasket analyze(String stringImput) {
		// 1 - Clean Chars Ignored and split text by space and remove ignore tokens
		String[] tokens = this.cleanChars(stringImput).split(" ");
		List<String> tokensCleaned = this.cleanTokens(tokens);
		
		// 2 - divide tokens numeric and no numeric
		List<String> tokensNoNumbers = new ArrayList<>();
		List<Double> tokensNumbers = new ArrayList<>();
		tokensCleaned.stream().forEach(
		    token -> 
			{try {
				double number = Double.parseDouble(token);
				tokensNumbers.add(number);
			} catch (NumberFormatException e) {
				tokensNoNumbers.add(token);			
			}});

		// 3 - Divide text is in Additional or Not
		List<String> tokensAdditionalTaxRate = new ArrayList<>();
		List<String> tokensNoAdditionalTaxRate = new ArrayList<>();
		
		tokensNoNumbers.stream()
		         .forEach(token -> 
		         {if (config.getParamTokensAdditionalTaxRate().contains(token)) {
		        	 tokensAdditionalTaxRate.add(token);
		         } else {
		        	 tokensNoAdditionalTaxRate.add(token);
		         }});
		
		// 4 - get amount and priceProduct
		double amountDetailBasket = this.getAmountDetailBasket(tokensNumbers);		
		double priceProductDetailBasket = this.getPriceProductDetailBasket(tokensNumbers);
		double taxRateBasic = this.getTaxRateBasic(tokensNoNumbers);
		double taxRateAdditional = this.getTaxRateAdditional(tokensNoNumbers);
		String nameDetailBasket = this.getNameDetailBasket(tokensAdditionalTaxRate,
				                                           tokensNoAdditionalTaxRate);

		return new DetailBasket(amountDetailBasket, 
				                nameDetailBasket, 
				                priceProductDetailBasket, 
				                taxRateBasic+taxRateAdditional);
	}

	private String cleanChars(String stringImput) {
		return stringImput.chars()
		.mapToObj(c -> (char) c)
		.filter(c -> !config.getParamIgnoreChar().contains(c))
		.map(c -> Character.toString(c))
		.collect(Collectors.joining());
	}

	private List<String> cleanTokens(String[] tokens) {
		return Stream.of(tokens)
				.filter(token -> !config.getParamIgnoreTokens().contains(token))
				.collect(Collectors.toList());
	}

	private Boolean findTokenFromList(List<String> words, 
			                          List<String> taxesPerWord) {
		return words.stream()
				.filter(token -> taxesPerWord.contains(token))
				.findFirst()
				.isPresent();
	}
	
	private double getPriceProductDetailBasket(List<Double> tokenNumbers) {
		if (tokenNumbers.size()>=1) {
			return tokenNumbers.get(tokenNumbers.size()-1);
		} else {
			return 0;
		}	
	}

	private double getAmountDetailBasket(List<Double> tokenNumbers) {
		if (tokenNumbers.size() >= 2)
			return tokenNumbers.get(0);
		else
			return 0;
	}
	
	private double getTaxRateBasic(List<String> tokensNoNumbers) {
		if (this.findTokenFromList(tokensNoNumbers, 
				                   config.getParamTokensZeroBasicTaxRate())) {
			return 0;
		} else {
			return config.getParamBasicTaxRate();
		} 
	}
	
	private double getTaxRateAdditional(List<String> tokensNoNumbers) {
		if (this.findTokenFromList(tokensNoNumbers, 
				                   config.getParamTokensAdditionalTaxRate())) {
			return config.getParamAdditionalTaxRate();
		} else {
			return 0;
		}
	}
	
	private String getNameDetailBasket(List<String> tokensAdditional, 
            List<String> tokensNoAdditional) {
		return Stream.concat(tokensAdditional.stream(), tokensNoAdditional.stream())
				.collect(Collectors.joining(" "));
	}

}
