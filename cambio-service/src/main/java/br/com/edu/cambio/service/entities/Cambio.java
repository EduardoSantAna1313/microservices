package br.com.edu.cambio.service.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "cambio")
public class Cambio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "from_currency", nullable = false, length = 3)
	private String from;

	@Column(name = "to_currency", nullable = false, length = 3)

	private String to;

	@Column(nullable = false)

	private BigDecimal conversionFactor;

	@Transient
	private BigDecimal convertedValue;

	@Transient
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

	public BigDecimal getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(final BigDecimal conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public BigDecimal getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(final BigDecimal convertedValue) {
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
				+ conversionFactor + ", convertedValue=" + convertedValue + ", environment='" + environment + '\''
				+ '}';
	}
}
