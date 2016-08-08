package classloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * Created by Dmitry on 07.08.2016.
 */
public class URLCustomClassLoader extends URLClassLoader {


    public URLCustomClassLoader(URL[] classpath) {
        super(classpath, null);
        System.out.println("Super is called on URL Custom Class Loader");
        System.out.println("URLS provided for loading class: " + Arrays.toString(classpath));

    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("Attempt to locate class by provided name" + name);
        return super.findClass(name);
    }
}




