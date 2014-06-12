package uk.co.taidev.experiments.correlationidasync;

import org.apache.http.client.*;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.*;
import org.springframework.web.client.*;
import uk.co.taidev.experiments.correlationidasync.web.filters.CorrelationHeaderFilter;

import java.util.*;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "uk.co.taidev.experiments.correlationidasync")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public FilterRegistrationBean correlationHeaderFilter() {
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new CorrelationHeaderFilter());
        filterRegBean.setUrlPatterns(Arrays.asList("/*"));

        return filterRegBean;
    }

	@Bean
	public HttpClient httpClient() {
		return HttpClients.custom().setConnectionManager(new PoolingHttpClientConnectionManager()).build();
	}

	@Bean
	public ClientHttpRequestFactory clientHttpRequestFactory() {
		return new HttpComponentsClientHttpRequestFactory(httpClient());
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
		restTemplate.setInterceptors(Collections.singletonList(new CorrelationHeaderInterceptor()));
		return restTemplate;
	}

}