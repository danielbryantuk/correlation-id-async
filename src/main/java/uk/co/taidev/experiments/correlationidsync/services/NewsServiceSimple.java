package uk.co.taidev.experiments.correlationidsync.services;

import org.springframework.stereotype.Service;
import uk.co.taidev.experiments.correlationidsync.reporting.RequestCorrelation;

/**
 * NewsServiceSimple
 */
@Service
public class NewsServiceSimple implements NewsService {

    @Override
    public String getNews() {
        //you probably wouldn't pollute service code with correlation Ids (instead only include them
        //when making external calls), but it is included here as an example of how to retrieve ids
        return String.format("No news is good news (with correlation Id '%s')", RequestCorrelation.getId());
    }

}
