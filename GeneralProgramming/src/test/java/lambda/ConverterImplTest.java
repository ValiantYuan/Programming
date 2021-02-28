package lambda;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ConverterImplTest {
    ConverterImpl converter = new ConverterImpl();
    @Test
    void convert() {
        String strValue = "564";
        Integer value = converter.convert(strValue);
        Assert.assertEquals(value, (Integer)564);
    }
}