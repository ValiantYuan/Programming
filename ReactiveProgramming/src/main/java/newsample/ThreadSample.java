package newsample;

import java.time.Duration;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : yuanqi
 * @since : 2021/1/14
 */
public class ThreadSample {
    public static void main(String[] args) {
//        Flux<String> helloPauseWorld =
//            Mono.just("Hello")
//                .concatWith(Mono.just("world")
//                    .delaySubscription(Duration.ofSeconds(1)));
//
//        helloPauseWorld.subscribe(System.out::println);

//        Mono<String> a = Mono.just("oops I'm late")
//            .delaySubscription(Duration.ofMillis(450));
//        Flux<String> b = Flux.just("let's get", "the party", "started")
//            .delaySubscription(Duration.ofMillis(500));
//
//        Flux.first(a, b)
//            .toIterable()
//            .forEach(System.out::println);

        long startTime = System.currentTimeMillis();
        Mono<String> string400 = getString400();
        Mono<String> string500 = getString500();
        long endTime = System.currentTimeMillis();
        Mono.zip(string400, string500)
            .map(item -> item.getT1() + item.getT2()).subscribe(System.out::println);



    }

    static Mono<String> getString400() {
        return Mono.just("i am wait 400 milliseconds");
    }

    static Mono<String> getString500() {
        return Mono.just("i am wait 500 milliseconds");
    }
}
