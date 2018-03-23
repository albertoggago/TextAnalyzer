package com.liferay.doc.alberto.analyzer;

import java.util.ArrayList;
import java.util.List;

public class Basket {
	private final List<DetailBasket> detailsBasket;
	private final double salesTaxes;
	private final double total;
	
	protected Basket(List<DetailBasket> detailsBasket) {
		this.detailsBasket = detailsBasket;
		this.salesTaxes = NumericFunctions.round(this.detailsBasket.stream()
				.mapToDouble(DetailBasket::getTaxes)				
				.sum());

		this.total = NumericFunctions.round(this.detailsBasket.stream()
				.mapToDouble(DetailBasket::getFinalPrice)				
				.sum());
	}

	protected Basket() {
		detailsBasket = new ArrayList<>();
		this.salesTaxes = 0;
		this.total = 0;
	}
	
	protected double getSalesTaxes() {return this.salesTaxes;} 
	protected double getTotal() {return this.total;}
	protected List<DetailBasket> getDetailBasket() {return this.detailsBasket;}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = (int) (prime * result + Double.doubleToLongBits(this.salesTaxes));
		result = (int) (prime * result + Double.doubleToLongBits(this.total));
		result = (int) (prime * result + this.detailsBasket.hashCode());
		return result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Basket [SalesTaxes=").append(salesTaxes)
		       .append(", Total=").append(total)
		       .append(", DetailsBasket=[").append(detailsBasket.toString())
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
		Basket other = (Basket) obj;
		if (hashCode() != other.hashCode()) {
			return false;
		}
		if (salesTaxes != other.salesTaxes) {
			return false;
		}
		if (total != other.total) {
			return false;
		}
		if (!detailsBasket.equals(other.detailsBasket) ) {
			return false;
		}
		return true;
	}
}
