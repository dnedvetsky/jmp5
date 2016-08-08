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

    public CustomClassLoader() {
        File fl = new File("target\\classes\\");
        try {
            System.out.print("Build path for " + fl.getAbsolutePath());
            myURL = new URL[]{fl.toURI().toURL()};
        } catch (MalformedURLException e) {
            System.out.println("Incorrect URL was used");
        }
    }

    public CustomClassLoader(String... strs) {
        List<URL> ls = new ArrayList<URL>();
        for (String str : strs) {
            try {
                ls.add(new File(str).toURI().toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        this.myURL = new URL[ls.size()];
        this.myURL = ls.toArray(myURL);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            customClassLoader = new URLCustomClassLoader(myURL);
            System.out.println("Attempt to locate provided path using URL class loader " + name);
            return customClassLoader.findClass(name);
        } catch (ClassNotFoundException e) {
            System.out.println("Use system class loader to load class with provided name");
            Class<?> loaded = defaultClassLoader.loadClass(name);
            if (loaded == null) {
                throw e;
            }
            return loaded;
        }
    }

}
