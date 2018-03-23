package com.alberto.analyzer;

import java.util.ArrayList;
import java.util.List;

import com.alberto.analyzer.config.Config;

public class PrintBasket {

	private Config config;
	private NumericFunctions numericFunctions;
	
	protected PrintBasket(Config config) {
		this.config = config;
		numericFunctions = new NumericFunctions(config);
	}

	protected String printBasket(Basket basket) {
		StringBuilder printBasketBuilder = new StringBuilder();
		for (DetailBasket detailBasket : basket.getDetailBasket()) {
			printBasketBuilder.append(printDetailBasket(detailBasket));
			printBasketBuilder.append("\n");
		}
		printBasketBuilder.append(printSales(basket));
		printBasketBuilder.append("\n");
		printBasketBuilder.append(printTotal(basket));
		return printBasketBuilder.toString();
	}

	protected String[] printBasketList(Basket basket) {
		List<String> printedList = new ArrayList<>();
		for (DetailBasket detailBasket : basket.getDetailBasket()) {
			printedList.add(printDetailBasket(detailBasket).toString());
		}
		printedList.add(printSales(basket).toString());
		printedList.add(printTotal(basket).toString());
		return printedList.toArray(new String[printedList.size()]);
	}
	
	private StringBuilder printTotal(Basket basket) {
		StringBuilder printTotalText = new StringBuilder();
		printTotalText.append(config.getParamInitPrintText())
		              .append(config.getParamMainBodyTotal())
		              .append(config.getParamEndPrintText())
		              .append(numericFunctions.formatFloat(basket.getTotal()));
		return printTotalText;
	}

	private StringBuilder printSales(Basket basket) {
		StringBuilder printSalesText = new StringBuilder();
		printSalesText.append(config.getParamInitPrintText())
		              .append(config.getParamMainBodySalesTaxes())
		              .append(config.getParamEndPrintText())
		              .append(numericFunctions.formatFloat(
		            		  basket.getSalesTaxes()));
		return printSalesText;
	}

	private StringBuilder printDetailBasket(DetailBasket detailBasket) {
		StringBuilder detailbasketText = new StringBuilder();
		detailbasketText.append(config.getParamInitPrintText())
						.append(numericFunctions.formatInteger(
								detailBasket.getAmount()))
						.append(" ")
						.append(detailBasket.getNameProduct())
						.append(config.getParamEndPrintText())
        				.append(numericFunctions.formatFloat(
        						detailBasket.getFinalPrice()));
		return detailbasketText;
	}
}
