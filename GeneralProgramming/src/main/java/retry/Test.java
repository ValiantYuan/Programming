package retry;

/**
 * @author : yuanqi
 * @since : 2020/6/9
 */
public class Test {
    int count = 0;


    public void accept() throws Exception {
        count++;
        if (count < 3) {
            throw new Exception("error");
        }
    }

    public static void main(String[] args) {

    }


}
