package org.abstruck.qwq.library.event.reflection;

import net.fabricmc.api.EnvType;
import org.abstruck.qwq.QwQ;
import org.abstruck.qwq.library.event.ModEvent;
import org.abstruck.qwq.library.event.SubscribeEvent;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;

/**
 * @author Goulixiaoji
 */
public class EventLoader extends EventHandle{
    private static final String FILE = "file";
    private static final String JAR = "jar";
    private static final String DOT_CLASS = ".class";

    public static void initEvent(String eventPath){
        EventLoader.initEvent(eventPath, true);
    }

    public static void initEvent(String eventPath, Boolean enable){
        eventPath = eventPath.replace(".", "/");
        if (EVENT_PATHS.containsKey(eventPath)){
            QwQ.LOGGER.warn("[EventLoader] You have given the same eventPath: " + eventPath);
            return;
        }
        EVENT_PATHS.put(eventPath, enable);

        try {
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(eventPath);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                if (url.getProtocol().equals(FILE)) {
                    List<File> fileList = new ArrayList<>();
                    EventLoader.listFiles(new File(url.getFile()), fileList);
                    EventLoader.loadClasses(fileList, eventPath);
                }

                else if (url.getProtocol().equals(JAR)) {
                    JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                    Enumeration<JarEntry> entries = jarURLConnection.getJarFile().entries();
                    EventLoader.loadJars(entries, eventPath);
                }
            }
        }
        catch (IOException | ClassNotFoundException e) {
            QwQ.LOGGER.error(e.getMessage());
        }
    }

    public static void reloadEvent(){
        for (Iterator<MethodWithClass> method = EVENT_METHODS.iterator(); method.hasNext(); ){
            method.remove();
        }

        for (Map.Entry<String, Boolean> paths : EVENT_PATHS.entrySet()){
            if (paths.getValue()){
                EventLoader.initEvent(paths.getKey());
            }
        }
    }

    public static void enableOrDisableEvent(String eventPath, Boolean isEnable){
        if (!EVENT_PATHS.containsKey(eventPath)){
            QwQ.LOGGER.error("[EventLoader] " + eventPath + " does not contain!");
            return;
        }
        EVENT_PATHS.put(eventPath, isEnable);
    }

    private static void listFiles(File dir, List<File> fileList){
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                EventLoader.listFiles(file, fileList);
            }
        } else {
            if (dir.getName().endsWith(DOT_CLASS)) {
                fileList.add(dir);
            }
        }
    }

    private static void loadJars(Enumeration<JarEntry> entries, String path) throws ClassNotFoundException {
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if(entry.getName().endsWith(DOT_CLASS)) {
                // \\ -> /
                String filePath = entry.getName().replaceAll("\\\\","/") ;
                if (filePath.indexOf(path) != 0){
                    continue;
                }
                filePath = filePath.replace(DOT_CLASS,"").replaceAll("/", ".");

                EventLoader.checkClass(filePath);
            }
        }
    }

    private static void loadClasses(List<File> classes, String path) throws ClassNotFoundException {
        for (File file : classes){
            // \\ -> /
            String filePath = file.getAbsolutePath().replaceAll("\\\\","/");
            String packageName = filePath.substring(filePath.lastIndexOf(path));
            packageName = packageName.replace(DOT_CLASS,"").replaceAll("/", ".");

            EventLoader.checkClass(packageName);
        }
    }

    private static void checkClass(String packageName) throws ClassNotFoundException {
        Class<?> klass = Class.forName(packageName);
        if (null != klass.getAnnotation(ModEvent.class)){
            EventLoader.loadMethods(klass);
        }
    }

    private static void loadMethods(Class<?> klass){
        Method[] methods = klass.getDeclaredMethods();
        for (Method method : methods){
            if (null != method.getAnnotation(SubscribeEvent.class)){

                Class<?>[] parameter = method.getParameterTypes();
                if (parameter.length != 1) {
                    QwQ.LOGGER.error("[EventLoader] " + klass.getName() + "::" + method.getName() + " can only have one parameter type.");
                    continue;
                }
                QwQ.LOGGER.info("[EventLoader] Have Subscribing Event: " + klass.getName() + "::" + method.getName() + "(" + parameter[0].getName() + ")");

                EVENT_METHODS.add(new MethodWithClass(klass, method));
            }
        }
    }
}
