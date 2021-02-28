package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @ClassName StreamBase
 * @Description: TODO
 * @Author: YuanQi
 * @Date: 2019/11/20
 */
public class StreamBase {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("abc", "dadfcd", "dtesc", "tesd");
        List<String> sortList = list.stream().sorted((a, b) -> b.length() - a.length()).collect(Collectors.toList());
        System.out.println(sortList);
        Integer totalLength = list.stream().filter(a -> a.contains("a")).mapToInt(String::length).sum();
        System.out.println(totalLength);
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

}
