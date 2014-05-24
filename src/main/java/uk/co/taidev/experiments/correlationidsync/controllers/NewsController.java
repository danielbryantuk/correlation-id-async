package uk.co.taidev.experiments.correlationidsync.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.taidev.experiments.correlationidsync.services.ExternalNewsService;
import uk.co.taidev.experiments.correlationidsync.services.NewsService;

/**
 * NewsController
 */
@RestController
public class NewsController {

    @Autowired
    private ExternalNewsService externalNewsService;

    @Autowired
    private NewsService newsService;


    @RequestMapping("externalNews")
    public String externalNews() {
        return externalNewsService.getNews();
    }


    @RequestMapping("news")
    public String news() {
        return newsService.getNews();
    }

}
