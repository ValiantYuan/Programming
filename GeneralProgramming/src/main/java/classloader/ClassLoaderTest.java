package classloader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : yuanqi
 */
public class ClassLoaderTest {
    public String hello = "hello";
    public int primitive = 1;
    public Integer packaging = 1;
    public List<Object> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[inputStream.available()];
                    inputStream.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Class<?> loadClass = myClassLoader.loadClass("classloader.ClassLoaderTest");
        Object obj = loadClass.newInstance();
        ClassLoaderTest classLoaderTest = new ClassLoaderTest();
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(classLoaderTest.getClass().getClassLoader());
        System.out.println(obj.getClass().getClassLoader().getParent());
        System.out.println(obj.getClass().getField("hello").getType().getClassLoader());
        System.out.println(obj.getClass().getField("primitive").getType().getClassLoader());
        System.out.println(obj.getClass().getField("packaging").getType().getClassLoader());
        System.out.println(obj.getClass());
        System.out.println(classLoaderTest.getClass());
        System.out.println(obj instanceof classloader.ClassLoaderTest);
        System.out.println(classLoaderTest instanceof classloader.ClassLoaderTest);
    }
}
