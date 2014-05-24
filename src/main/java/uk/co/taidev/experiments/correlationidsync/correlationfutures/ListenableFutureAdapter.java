package uk.co.taidev.experiments.correlationidsync.correlationfutures;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * ListenableFutureAdapter
 */
public class ListenableFutureAdapter<T> extends DeferredResult<String> {

    public ListenableFutureAdapter(final ListenableFuture<T> target) {
        Futures.addCallback(target, new FutureCallback<T>() {
            @Override
            public void onSuccess(T result) {
                setResult(result.toString());
            }

            @Override
            public void onFailure(Throwable t) {
                setErrorResult(t);
            }
        });
    }
}