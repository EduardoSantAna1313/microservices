package br.com.edu.book.service.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "author", nullable = false)
	private String author;

	@Column(name = "launch_date", nullable = false)
	private Date launchDate;
	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@Column(name = "title", nullable = false)
	private String title;

	@Transient
	private String currency;

	@Transient
	private String environment;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(final Date launchDate) {
		this.launchDate = launchDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(final String environment) {
		this.environment = environment;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", launchDate=" + launchDate + ", price=" + price + ", title="
				+ title + ", currency=" + currency + ", environment=" + environment + "]";
	}

}
