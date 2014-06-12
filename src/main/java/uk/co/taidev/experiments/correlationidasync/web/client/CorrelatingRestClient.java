package uk.co.taidev.experiments.correlationidasync.web.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uk.co.taidev.experiments.correlationidasync.reporting.RequestCorrelation;

/**
 * CorrelatingRestClient
 */
@Component
public class CorrelatingRestClient implements RestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CorrelatingRestClient.class);

	private RestTemplate restTemplate;

	@Autowired
	public CorrelatingRestClient( RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
    public String getForString(String uri) {
        String correlationId = RequestCorrelation.getId();

        LOGGER.info("start REST request to {} with correlationId {}", uri, correlationId);

        //TODO: error-handling and fault-tolerance in production
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET,
                new HttpEntity<String>(new HttpHeaders()), String.class);

        LOGGER.info("completed REST request to {} with correlationId {}", uri, correlationId);

        return response.getBody();
    }
}
