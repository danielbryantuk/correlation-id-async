package uk.co.taidev.experiments.correlationidsync.correlationfutures;

import uk.co.taidev.experiments.correlationidsync.reporting.RequestCorrelation;

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
