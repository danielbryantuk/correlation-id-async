package uk.co.taidev.experiments.correlationidasync;

import org.springframework.http.*;
import org.springframework.http.client.*;
import uk.co.taidev.experiments.correlationidasync.reporting.*;

import java.io.*;

public class CorrelationHeaderInterceptor implements ClientHttpRequestInterceptor {


	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws
			IOException {

		HttpHeaders headers = request.getHeaders();
		headers.add(RequestCorrelation.CORRELATION_ID_HEADER, RequestCorrelation.getId());
		return execution.execute(request, body);
	}
}
