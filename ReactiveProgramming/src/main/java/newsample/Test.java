package newsample;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

/**
 * @author : yuanqi
 * @since : 2020/12/8
 */
public class Test {
    public static void main(String[] args) {
        MySubscriber mySubscriber = new MySubscriber();
        Flux.just("cat","dog","mouse")
            .doOnRequest(n -> System.out.println("订阅者发起请求数据，请求长度" + n))
            .subscribe(mySubscriber);
    }

}
class MySubscriber implements Subscriber<String> {
    volatile Subscription subscription;
    private final int i = 2;
    int j = 0;

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("已发起订阅");
        this.subscription = subscription;
        subscription.request(i);
    }

    @Override
    public void onNext(String value) {
        j++;
        System.out.println("出版商发送来了数据====" + value + "===第" + j + "条");
        if (j == i) {
            j = 0;
            subscription.request(i);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("数据发送异常");
        throw Exceptions.errorCallbackNotImplemented(throwable);
    }

    @Override
    public void onComplete() {
        System.out.println("数据已发送完");
    }
}