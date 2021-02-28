package lambda;

/**
 * @ClassName ConverterImpl
 * @Description: TODO
 * @Author: YuanQi
 * @Date: 2019/11/20
 */
public class ConverterImpl {

    public Integer convert(String strValue) {
        Converter<String, Integer> converter = Integer::valueOf;
        return converter.convert(strValue);
    }
}
