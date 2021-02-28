import java.io.Console;
import java.util.Scanner;

/**
 * @ClassName Print
 * @Description: TODO
 * @Author: YuanQi
 * @Date: 2019/11/26
 */
public class Print {
    public static void main(String[] args) {
        while(true) {
            Console console = System.console();
            String name = console.readLine();
            System.out.println("Hello " + name);
        }
    }
}
