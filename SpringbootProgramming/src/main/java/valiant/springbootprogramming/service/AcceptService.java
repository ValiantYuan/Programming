package valiant.springbootprogramming.service;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

/**
 * @author : yuanqi
 * @since : 2020/6/9
 */
@Component
public class AcceptService {
    int count = 0;
    public int accept() throws Exception {
        count++;
        if (count < 4) {
            throw new Exception("error");
        }
        return count;
    }

    @Retryable
    public int retryMethod() throws Exception {
        return accept();
    }

//    public int retryMethodImperative() {
//        RetryTemplate template = RetryTemplate;
//    }

    @Recover
    public int test(Exception e) throws Exception{
        System.out.println("retry aborted");
        return 100;
    }
}
