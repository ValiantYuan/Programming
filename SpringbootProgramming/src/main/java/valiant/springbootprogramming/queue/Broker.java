package valiant.springbootprogramming.queue;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : yuanqi
 * @since : 2021/4/7
 */
public class Broker {
    //存放topic
    Map<String, Topic> topics = new HashMap<>();
    //存放topic的锁
    Map<String, Lock> lockmap;
    boolean enqueue(String topic, String msg, LocalDateTime producedTime) {
        Msg msg1 = new Msg();
        Lock lock = lockmap.get(topic);
        lock.lock();
        try {
            // 如果当前队列是满的，需要做删除操作

        }finally {
            lock.unlock();
        }


        synchronized(lock) {

        }
        return true;
    }

    Object dequeue(String topic, String consumerGroup) {
        // 出队列也要加锁,与入队是同一把锁
    }

}

class Topic {
    Msg[] queue;
    List<Msg>[] maxTimedMsg = new List[];
    0  1 2 3 4
    M1 MN+1
    MN M

    Map<String, List<Integer>> consumerRecord;
    int head;
    int tail;
    public Topic(int size) {
        queue = new Msg[size];
    }
}

class Msg {
    String msg;
    LocalDateTime enqueueTime;
    LocalDateTime producedTime;

    public Msg(String msg, LocalDateTime enqueueTime, LocalDateTime producedTime) {
        this.msg = msg;
        this.enqueueTime = enqueueTime;
        this.producedTime = producedTime;
    }
}
