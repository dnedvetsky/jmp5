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
    /**
     * Method is used to locate class. Check to return class if it's already loaded.
     */
    public Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("Attempt to locate class by provided name" + name);
        Class<?> loadedClass = findLoadedClass(name);
        if (loadedClass != null) {
            System.out.println("Class " + name + "is already loaded and returned as it is");
            return loadedClass;
        }
        return super.findClass(name);
    }
}




