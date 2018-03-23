package com.liferay.doc.alberto.analyzer;

public class DetailBasket {
	private final double amount;
	private final String nameProduct;
	private final double priceProduct;
	private final double taxes;
	private final double finalPrice;
	
	protected DetailBasket(double amount, 
			             String nameProduct, 
			             double priceProduct,
			             double taxRate){
		this.amount = amount;
		this.nameProduct = nameProduct;
		this.priceProduct = priceProduct;
		// taxes =  round(pricePproduct *taxRate / 100) * amount
		this.taxes = NumericFunctions.round(
				NumericFunctions.roundTax(this.priceProduct*taxRate/100.0)*this.amount);
		// finalPrice =  priceProduct * amount + taxes
		this.finalPrice = NumericFunctions.round(this.priceProduct * this.amount + this.taxes);
	}
	
	protected double getTaxes() {return this.taxes;} 
	protected double getFinalPrice() {return this.finalPrice;} 
	protected String getNameProduct() {return this.nameProduct;} 
	protected double getAmount() {return this.amount;}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = (int) (prime * result + Double.doubleToLongBits(amount));
		result = (int) (prime * result + nameProduct.hashCode());
		result = (int) (prime * result + Double.doubleToLongBits(priceProduct));
		result = (int) (prime * result + Double.doubleToLongBits(taxes));
		result = (int) (prime * result + Double.doubleToLongBits(finalPrice));
		return result;
	}

	@Override

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Detail Basket [Amount=").append(amount)
		       .append(", Name product=").append(nameProduct)
		       .append(", Price product=").append(priceProduct)
		       .append(", Taxes=").append(taxes)
		       .append(", Final price=").append(finalPrice)
		       .append("]");
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DetailBasket other = (DetailBasket) obj;
		if (hashCode() != other.hashCode()) {
			return false;
		}
		if (amount != other.amount) {
			return false;
		}
		if (!nameProduct.equals(other.nameProduct)) {
			return false;
		}
		if (priceProduct != other.priceProduct ) {
			return false;
		}
		if (taxes != other.taxes ) {
			return false;
		}
		if (finalPrice != other.finalPrice ) {
			return false;
		}
		return true;
	}

}