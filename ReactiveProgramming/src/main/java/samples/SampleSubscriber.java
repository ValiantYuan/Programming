package samples;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;

/**
 * @author : yuanqi
 * @since : 2020/11/6
 */
public class SampleSubscriber<T> extends BaseSubscriber<T> {
    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("subscribed");
        request(1);
    }

    @Override
    protected void hookOnNext(T value) {
        System.out.println(value);
        request(1);
    }
}
