package uk.co.taidev.experiments.correlationidsync.controllers;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import uk.co.taidev.experiments.correlationidsync.correlationfutures.CorrelationCallable;
import uk.co.taidev.experiments.correlationidsync.correlationfutures.ListenableFutureAdapter;
import uk.co.taidev.experiments.correlationidsync.services.ExternalNewsService;
import uk.co.taidev.experiments.correlationidsync.services.NewsService;

import java.util.concurrent.Executors;

/**
 * NewsController
 */
@RestController
public class NewsController {

    @Autowired
    private ExternalNewsService externalNewsService;

    @Autowired
    private NewsService newsService;

    private ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));


    @RequestMapping("externalNews")
    public DeferredResult<String> externalNews() {
        return new ListenableFutureAdapter<>(service.submit(new CorrelationCallable<>(externalNewsService::getNews)));
    }


    @RequestMapping("news")
    public String news() {
        return newsService.getNews();
    }

}
