package valiant.springbootprogramming.component;

import org.springframework.stereotype.Component;

/**
 * @ClassName People
 * @Description:
 * @Author: YuanQi
 * @Date: 2020/4/23
 */
@Component
public class Person {
    String name = "joe";

    public String introduce() {
        return "my name is " + name;
    }
}
