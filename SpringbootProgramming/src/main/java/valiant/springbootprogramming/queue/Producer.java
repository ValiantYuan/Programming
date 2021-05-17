package valiant.springbootprogramming.queue;

import java.time.LocalDateTime;

/**
 * @author : yuanqi
 * @since : 2021/4/7
 */
public class Producer {


    Broker broker;

    boolean sendMsg(String topic, Object msg) {

        broker.enqueue(topic, msg, LocalDateTime.now());
    }
}
