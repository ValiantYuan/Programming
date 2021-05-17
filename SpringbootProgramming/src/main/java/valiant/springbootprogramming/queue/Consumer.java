package valiant.springbootprogramming.queue;

/**
 * @author : yuanqi
 * @since : 2021/4/7
 */
public class Consumer {

    Broker broker;


    Object getMsg(String topic, String consumerGroup) {
        Object o = broker.dequeue(topic, consumerGroup);
        return o;
    }



}
