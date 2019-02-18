package com.ur.java.demo.spring.ioc.ano;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UrAnnotationApplicationContext {
    private String path;
    private ConcurrentHashMap<String, Object> context;

    public UrAnnotationApplicationContext(String path) {
        this.path = path;
        scanClassExistAnnotation();


    }

    //扫描path包下所有的类是否添加注解
    private void scanClassExistAnnotation() {
        List<Class<?>> allClasses = ClassUtils.getClasses(path);
        allClasses.forEach(clzz -> {
            UrService urService = clzz.getAnnotation(UrService.class);
            if (urService != null) {
                String beanId = toLowerCaseFirstOne(clzz.getSimpleName());
                Object bean = newInstance(clzz);
                if (context == null) {
                    this.context = new ConcurrentHashMap<>();
                }
                context.put(beanId, bean);
            }
        });
        //DI注入
        context.entrySet().forEach(set -> {
            Object instance = set.getValue();
            Class<?> clzz = instance.getClass();
            Field[] fields = clzz.getDeclaredFields();
            for (Field field : fields) {
                UrAutowired autowired = field.getAnnotation(UrAutowired.class);
                if (autowired != null) {
                    try {
                        if (field.isAccessible() == false) {
                            field.setAccessible(true);
                        }
                        field.set(instance, context.get(toLowerCaseFirstOne(field.getType().getSimpleName())));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    // 首字母转小写
    private String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return String.valueOf(Character.toLowerCase(s.charAt(0))) + s.substring(1);
    }

    private Object newInstance(Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("实例化失败");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("实例化失败-非法权限");
        }
    }
    //添加注解，放入容器中

    //提供getBean方法
    public Object getBean(String beanId) {
        return context.get(beanId);
    }
}


