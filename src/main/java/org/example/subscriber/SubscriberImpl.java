package org.example.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriberImpl implements Subscriber<String> {

    private static final Logger logger = LoggerFactory.getLogger(SubscriberImpl.class);
    private Subscription subscription;

    public Subscription geSubscription() {
        return subscription;
    }

    @Override
    public void onComplete() {
        logger.info("Completed!");
    }

    @Override
    public void onError(Throwable throwable) {
        logger.error("Error", throwable);
    }

    @Override
    public void onNext(String email) {
        logger.info("Received: {}", email);
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }
}
