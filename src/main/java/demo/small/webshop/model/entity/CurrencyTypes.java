package demo.small.webshop.model.entity;

public enum CurrencyTypes {
	HUF("HUF"), EURO("EURO"), USD("USD"), GBP("GBP");

	private final String value;

	private CurrencyTypes(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
