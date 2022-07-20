package br.com.edu.api.gateway.config;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class OpenApiCOnfiguration {

	@Bean
	@Lazy(false)
	public List<GroupedOpenApi> apis(final SwaggerUiConfigParameters config, final RouteDefinitionLocator locator) {

		final var list = locator.getRouteDefinitions().collectList().block();

		list.stream().filter(d -> d.getId().matches(".*-service")).forEach(d -> {
			final String name = d.getId();
			config.addGroup(name);
			GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
		});

		return new ArrayList<>();
	}

}
