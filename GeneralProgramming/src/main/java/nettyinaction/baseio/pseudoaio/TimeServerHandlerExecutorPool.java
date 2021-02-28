package nettyinaction.baseio.pseudoaio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : yuanqi
 * @since : 2020/7/20
 */
public class TimeServerHandlerExecutorPool {
    private ExecutorService executor;

    public TimeServerHandlerExecutorPool(int maxPoolSize, int queueSize) {
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            maxPoolSize,
            120L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void execute(Runnable task) {
        executor.execute(task);
    }
}
