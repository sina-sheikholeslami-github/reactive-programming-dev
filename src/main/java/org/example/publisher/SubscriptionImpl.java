package org.example.publisher;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;

public class SubscriptionImpl implements Subscription {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);
    private static final int MAX_ITEMS = 10;
    private final Faker faker;
    private final Subscriber subscriber;
    private boolean isCanceled; 
    private int count = 0;

    public SubscriptionImpl(Subscriber subscriber) {
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void request(long n) {
        if (isCanceled) {
            return;
        }
        log.info("Subscriber has requested {} items.", n);
        for (int i = 0; i < n && count < MAX_ITEMS; i++) {
            count++;
            this.subscriber.onNext(this.faker.internet().emailAddress());
        }
        if (count == MAX_ITEMS) {
            log.info("No more data to produce");
            this.subscriber.onComplete();
            this.isCanceled = true;
        }
    }

    @Override
    public void cancel() {
        log.info("Subscriber has cancelled");
        this.isCanceled = true;
    }

}
