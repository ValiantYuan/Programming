import reactor.core.publisher.Flux;
import samples.SampleSubscriber;

/**
 * @author : yuanqi
 * @since : 2020/11/6
 */
public class FluxTest {
    public static void main(String[] args) {

        // 只订阅，什么都不干
        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe();

        // 带上了处理功能
        ints.subscribe(i -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        });

        // 带上了处理完成后续操作
        ints.subscribe(i -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }, System.err::println, () -> System.out.println("Done"));
        SampleSubscriber<Integer> sampleSubscriber = new SampleSubscriber<>();
        ints.subscribe(sampleSubscriber);


        // 处理 + 异常处理
        ints = Flux.range(1, 4)
        .map(i -> {
            if(i <= 3) {
                return i;
            } else {
                throw new RuntimeException("Got to 4");
            }
        });
        ints.subscribe(System.out::println, System.err::println);

        // 增加了订阅处理，支持
        ints = Flux.range(1, 20);
        ints.subscribe(System.out::println, System.err::println, () -> System.out.println("Done"), subscription -> subscription.request(10));


    }
}
