package br.com.edu.greeting.service.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties("greeting-service")
public class GreetingConfiguration {

	private String greeting;

	private String defaultValue;

	public GreetingConfiguration() {
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(final String greeting) {
		this.greeting = greeting;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(final String defaultValue) {
		this.defaultValue = defaultValue;
	}
}