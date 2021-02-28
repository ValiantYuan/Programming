package errors.concurrency;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author : yuanqi
 * @since : 2021/2/28
 */
@RestController
@RequestMapping("/concurrency")
@Slf4j
public class ConcurrencyController {
    private static int THREAD_COUNT = 10;
    private static int ITEM_COUNT = 1000;

    private ConcurrentHashMap<String, Long> getData(int count) {
        return LongStream.rangeClosed(1, count)
            .boxed()
            .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(),
                Function.identity(),
                (o1, o2) -> o1,
                ConcurrentHashMap::new));
    }

    @GetMapping("/sizeError")
    public String wrong() throws InterruptedException {
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
        log.info("init size : {}", concurrentHashMap.size());

        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);

        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            int gap = ITEM_COUNT - concurrentHashMap.size();
            log.info("gap size {}", gap);
            concurrentHashMap.putAll(getData(gap));
        }));

        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.SECONDS);
        log.info("finish size {}", concurrentHashMap.size());
        return "OK";
    }

}
