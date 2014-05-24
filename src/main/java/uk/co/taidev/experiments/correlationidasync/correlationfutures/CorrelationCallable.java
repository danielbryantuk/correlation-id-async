package uk.co.taidev.experiments.correlationidasync.correlationfutures;

import uk.co.taidev.experiments.correlationidasync.reporting.RequestCorrelation;

import java.util.concurrent.Callable;

/**
 * CorrelationCallable
 */
public class CorrelationCallable<V> implements Callable<V> {

    private String correlationId;
    private Callable<V> callable;

    public CorrelationCallable(Callable<V> targetCallable) {
        correlationId = RequestCorrelation.getId();
        callable = targetCallable;
    }

    @Override
    public V call() throws Exception {
        RequestCorrelation.setId(correlationId);
        return callable.call();
    }
}
