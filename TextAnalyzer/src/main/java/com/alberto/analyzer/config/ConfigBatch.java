package com.alberto.analyzer.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigBatch extends Config {

	
	public ConfigBatch(String fileConfig) throws FileNotFoundException, 
	                                                IOException, 
	                                                ParseException {
		super();
		JSONObject jsonConfig = 
		      (JSONObject) (new JSONParser()).parse(new FileReader(new File(fileConfig)));

		paramBasicTaxRate = Double.parseDouble(jsonConfig.get("basicTaxRate").toString());

		JSONArray jsonTokensZeroBasicTaxRate = (JSONArray) jsonConfig.get("tokensZeroBasicTaxRate");
		paramTokensZeroBasicTaxRate = this.createListTokensTaxes(jsonTokensZeroBasicTaxRate);

		paramAdditionalTaxRate = Double.parseDouble(jsonConfig.get("additionalTaxRate").toString());

		JSONArray jsonTokensAdditionalTaxRate = (JSONArray) jsonConfig.get("tokensAdditionalTaxRate");
		paramTokensAdditionalTaxRate = this.createListTokensTaxes(jsonTokensAdditionalTaxRate);
		
		JSONArray jsonIgnoreChar = (JSONArray) jsonConfig.get("ignoreChar");
		paramIgnoreChar = this.getListCharsfromJson(jsonIgnoreChar);

		JSONArray jsonIgnoreTokens = (JSONArray) jsonConfig.get("ignoreTokens");
		paramIgnoreTokens = this.getListStringfromJson(jsonIgnoreTokens);

		paramInitPrintText = jsonConfig.get("initPrintText").toString();

		paramEndPrintText = jsonConfig.get("endPrintText").toString();
			
		paramMainBodySalesTaxes = jsonConfig.get("mainBodySalesTaxes").toString();
		paramMainBodyTotal = jsonConfig.get("mainBodyTotal").toString();
	}

	@SuppressWarnings("unchecked")
	private List<String> createListTokensTaxes(JSONArray jsonArrayTokens) {
		return (List<String>) jsonArrayTokens.stream()
				.map(Object::toString)
				.collect(Collectors.toList());
	}

	private List<Character> getListCharsfromJson(JSONArray jsonStringEntrada) {
		@SuppressWarnings("unchecked")
		String charToIgnore = (String) jsonStringEntrada.stream()
				   .map(Object::toString)
		           .collect(Collectors.joining());
		return charToIgnore.chars()
				           .mapToObj(c -> (char) c)
				           .collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	private List<String> getListStringfromJson(JSONArray jsonIgnoreWords) {
		return (List<String>) jsonIgnoreWords.stream()
						.map(Object::toString)
						.collect(Collectors.toList());
	}

}
