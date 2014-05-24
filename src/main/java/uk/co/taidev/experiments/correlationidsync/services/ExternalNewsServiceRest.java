package uk.co.taidev.experiments.correlationidsync.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.taidev.experiments.correlationidsync.web.client.RestClient;

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
        //in production better error-handling and fault-tolerance would be required here
        return restClient.getForString(NEWS_LOCATION);
    }
}
