package classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 07.08.2016.
 */
public class CustomClassLoader extends ClassLoader {
    private URLCustomClassLoader customClassLoader;
    private ClassLoader defaultClassLoader = CustomClassLoader.getSystemClassLoader();
    private URL myURL[];

    /**
     * This is a default constructor that uses default path for classes
     */
    public CustomClassLoader() {
        File fl = new File("target\\classes\\");
        try {
            System.out.print("Build path for " + fl.getAbsolutePath());
            myURL = new URL[]{fl.toURI().toURL()};
        } catch (MalformedURLException e) {
            System.out.println("Incorrect URL was used");
        }
        customClassLoader = new URLCustomClassLoader(myURL);
    }

    /**
     * THis constructor allows to specify path for resources
     * @param strs - paths
     */
    public CustomClassLoader(String... strs) {
        List<URL> ls = new ArrayList<URL>();
        for (String str : strs) {
            try {
                ls.add(new File(str).toURI().toURL());
            } catch (MalformedURLException e) {
            }
        }
        this.myURL = new URL[ls.size()];
        this.myURL = ls.toArray(myURL);
        customClassLoader = new URLCustomClassLoader(myURL);
    }

    /**
     * This method is used to load class. First it calls URL class loader and on a failed attempt it tries to load class
     * by it's name using system classloader
     * @param name - class name
     * @return Class
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            System.out.println("Attempt to locate provided path using URL class loader " + name);
            return customClassLoader.findClass(name);
        } catch (ClassNotFoundException e) {
            System.out.println("Use system class loader to load class with provided name");
            Class<?> loadedClass = findLoadedClass(name) != null ? findLoadedClass(name) :
                    defaultClassLoader.loadClass(name);
            if (loadedClass == null) {
                throw e;
            }
            return loadedClass;
        }
    }

}
