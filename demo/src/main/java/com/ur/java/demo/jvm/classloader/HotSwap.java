package com.ur.java.demo.jvm.classloader;

import com.ur.java.demo.common.MD5Utils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class HotSwap {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException, InterruptedException {
        invokeMethod();
//        System.gc();
//        Thread.sleep(5000);
//        File file = new File("D:\\HotUser.class");
        File workFile = new File("D:\\IdeaProjects\\demo\\target\\classes\\com\\ur\\java\\demo\\jvm\\classloader\\HotUser.class");
        String fileMd5 = MD5Utils.getFileMD5(workFile);
//        log.info("新文件hash:" + MD5Utils.getFileMD5(file));
//        log.info("旧文件hash:" + MD5Utils.getFileMD5(workFile));
//        try {
//            boolean isdelete = workFile.delete();
//            if(!isdelete){
//                log.info("删除文件失败");
//            }
//            FileUtils.moveFile(file, workFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.info("替换文件失败");
//            return;
//        }
        while (true) {
            String newfileMd5 = MD5Utils.getFileMD5(workFile);
            if (!fileMd5.equals(newfileMd5)) {
                fileMd5 = newfileMd5;
                log.info(fileMd5);
                invokeMethod();
            } else {
                log.info("class文件未改变");
            }
            System.gc();
            Thread.sleep(5000);
        }

//        invokeMethod();
    }

    public static void invokeMethod() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        HotClassLoader classLoader = new HotClassLoader();

        Class<?> findClass = classLoader.findClass("com.ur.java.demo.jvm.classloader.HotUser");

        Object obj = findClass.newInstance();

        Method method = findClass.getMethod("add");

        method.invoke(obj);

    }
}
