package lambda;

/**
 * @ClassName Converter
 * @Description: TODO
 * @Author: YuanQi
 * @Date: 2019/11/20
 */
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}
