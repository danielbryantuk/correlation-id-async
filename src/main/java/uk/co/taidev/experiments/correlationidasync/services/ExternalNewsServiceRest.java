package uk.co.taidev.experiments.correlationidasync.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.taidev.experiments.correlationidasync.web.client.RestClient;

/**
 * ExternalNewsServiceRest
 */
@Service
public class ExternalNewsServiceRest implements ExternalNewsService {

    private static final String NEWS_LOCATION = "http://localhost:8080/news";


    @Autowired
    private RestClient restClient;


    @Override
    public String getNews() {
        //TODO: error-handling and fault-tolerance would be required here in production code
        return restClient.getForString(NEWS_LOCATION);
    }
}
