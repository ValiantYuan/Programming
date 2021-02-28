package orika;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * @author : yuanqi
 * @since : 2020/8/19
 */
public class MetaspaceOverflow {
    public static void main(String[] args) {
        while (true) {
            MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
            mapperFactory.classMap(User.class, Student.class).exclude("gender").byDefault().register();
            MapperFacade mapper = mapperFactory.getMapperFacade();
            System.out.println("running");
//            System.out.println(mapper);
        }
    }
}
