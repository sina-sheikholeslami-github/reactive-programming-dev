package org.example.publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class PublisherImpl implements Publisher {

    @Override
    public void subscribe(Subscriber subscriber) {

        var subscription = new SubscriptionImpl(subscriber);
        subscriber.onSubscribe(subscription);
    }

}
