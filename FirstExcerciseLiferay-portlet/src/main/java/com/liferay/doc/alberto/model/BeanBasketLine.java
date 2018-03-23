package com.liferay.doc.alberto.model;

public class BeanBasketLine {
	private String line;

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
	
	public BeanBasketLine() {
		   this.line = null;
		}

		public BeanBasketLine(String line) {
		   setLine(line);
		}
	
}
