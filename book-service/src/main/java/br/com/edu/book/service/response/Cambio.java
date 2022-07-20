package br.com.edu.book.service.response;

public class Cambio {

	private Long id;

	private String from;

	private String to;

	private Double conversionFactor;

	private Double convertedValue;

	private String environment;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(final String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(final String to) {
		this.to = to;
	}

	public Double getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(final Double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public Double getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(final Double convertedValue) {
		this.convertedValue = convertedValue;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(final String environment) {
		this.environment = environment;
	}

	@Override
	public String toString() {
		return "Cambio{" + "id=" + id + ", from='" + from + '\'' + ", to='" + to + '\'' + ", conversionFactor="
				+ conversionFactor + ", convetedValue=" + convertedValue + ", environment='" + environment + '\'' + '}';
	}
}
