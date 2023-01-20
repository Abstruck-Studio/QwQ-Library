package org.abstruck.qwq.library.event.asm;

import org.abstruck.qwq.QwQ;
import org.abstruck.qwq.library.event.SubscribeEvent;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;

import static org.abstruck.qwq.library.event.asm.EventManager.TASK_METHODS;

/**
 * @author Goulixiaoji
 */
public class EventLoader {
    private static final String FILE = "file";
    private static final String JAR = "jar";

    public static void init(String path){

        path = path.replace(".", "/");

        try {

            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(path);

            while(dirs.hasMoreElements()) {
                URL url = dirs.nextElement() ;
                if(url.getProtocol().equals(FILE)) {
                    List<File> classes = new ArrayList<File>();
                    listFiles(new File(url.getFile()), classes);
                    loadClasses(classes, path);
                }
                else if(url.getProtocol().equals(JAR)) {
                    JarURLConnection urlConnection = (JarURLConnection) url.openConnection();
                    Enumeration<JarEntry> entries = urlConnection.getJarFile().entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                    }
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            QwQ.LOGGER.error(e.getMessage());
        }

    }

    private static void loadClasses(List<File> classes, String scan) throws ClassNotFoundException, IOException {

        for(File file : classes) {
            String fPath = file.getAbsolutePath().replaceAll("\\\\","/") ;
            String packageName = fPath.substring(fPath.lastIndexOf(scan));
            packageName = packageName.replace(".class","").replaceAll("/", ".");

            Class<?> clazz = Class.forName(packageName);

            ClassReader classReader = new ClassReader(clazz.getName());
            ClassNode classNode = new ClassNode();

            classReader.accept(classNode, 0);
            List<AnnotationNode> annotations = classNode.visibleAnnotations;
            if (annotations != null){
                for (AnnotationNode annotationNode : annotations){
                    String anno = annotationNode.desc.replaceAll("/", ".");
                    String annoName = anno.substring(1, anno.length() - 1);
                    if (annoName.equals("org.abstruck.qwq.library.event.ModEvent")){
                        QwQ.LOGGER.info(clazz.getName());
                        loadMethods(clazz);
                        break;
                    }
                }
            }
        }
    }

    private static void loadMethods(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods){
            if (null != method.getAnnotation(SubscribeEvent.class)){
                EventManager.ClassWithMethod classWithMethod = new EventManager.ClassWithMethod(clazz, method);
                TASK_METHODS.add(classWithMethod);
            }
        }

    }

    private static void listFiles(File dir, List<File> fileList) {
        if (dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                listFiles(f, fileList);
            }
        } else {
            if(dir.getName().endsWith(".class")) {
                fileList.add(dir);
            }
        }
    }


}

