package com.alberto.analyzer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alberto.analyzer.config.Config;

public class ReadBasket {

	private final AnalyzeDetailBasket analyzeDetailBasket;
	
	protected ReadBasket(Config config) {
		analyzeDetailBasket = new AnalyzeDetailBasket(config);
	}

	protected Basket readBasketFile(String file) throws IOException {
		Stream<String> lines = Files
				.lines(Paths.get(file), Charset.defaultCharset());
		return this.getBasket(lines);
	}

	protected Basket readBasket(String[] lines)  {
		Stream<String> linesStream = Stream.of(lines);

		return this.getBasket(linesStream);
	}

	protected Basket readBasket(String textComplet)  {
		Stream<String> linesStream = Stream.of(textComplet.split("\\n"));

		return this.getBasket(linesStream);
	}

	protected Basket getBasket(Stream<String> streamLines) {
		return new Basket(streamLines
				.map(line -> analyzeDetailBasket.analyze(line))
				.collect(Collectors.toList()));
	}

}
