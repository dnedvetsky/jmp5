package main;

import classloader.CustomClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Scanner;

/**
 * Created by Dmitry on 07.08.2016.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Scanner sc = new Scanner(System.in);
        String line = "";
        CustomClassLoader classLoader = new CustomClassLoader(sc.nextLine());
        while (!line.equals("exit")) {
            line = sc.nextLine();
            if (line.equals("exit")) break;
            Class<?> cl = classLoader.loadClass(line);
            cl.getDeclaredMethod("lever").invoke(cl.newInstance());
        }
    }
}
