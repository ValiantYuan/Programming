package valiant.springbootprogramming;

/**
 * @author : yuanqi
 * @since : 2021/4/7
 */
public class Queue {
    Object lock;

    boolean enqueue(String topic, Object msg) {
        synchronized(lock) {

        }
        return true;
    }

    Object dequeue(String topic) {

    }



}
