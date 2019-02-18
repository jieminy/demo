package com.ur.java.demo.spring.mvc;


import com.ur.java.demo.spring.ioc.ano.ClassUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UrDispatcherServlet extends HttpServlet {
    private ConcurrentHashMap<String, Object> context = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, UrlHandlerSupport> urlMappings = new ConcurrentHashMap<>();

    @Override
    public void init() throws ServletException {
        //1.扫描包，将@UrController注解的类加载到容器中
        List<Class<?>> classList = ClassUtils.getClasses("com.ur.java.demo.spring.mvc");
        initContext(classList);
        //2.建立url与方法的映射
        buildHandlerMapping();
    }

    private void buildHandlerMapping() {
        context.entrySet().forEach(set -> {
            Object instance = set.getValue();
            Class<?> clzz = instance.getClass();
            UrRequestMapping requestMapping = clzz.getDeclaredAnnotation(UrRequestMapping.class);
            String url = "";
            if (requestMapping != null) {
                url += requestMapping.value();
            }
            Method[] methods = clzz.getMethods();
            for (Method method : methods) {
                UrRequestMapping mapping = method.getDeclaredAnnotation(UrRequestMapping.class);
                if (mapping != null) {
                    url += mapping.value();
                    urlMappings.put(url, new UrlHandlerSupport(instance, method));
                }
            }
        });
    }

    private void initContext(List<Class<?>> classes) {
        classes.forEach(clzz -> {
            UrController urController = clzz.getDeclaredAnnotation(UrController.class);
            if (urController != null) {
                String beanId = ClassUtils.toLowerCaseFirstOne(clzz.getSimpleName());
                Object bean = newInstance(clzz);
                context.put(beanId, bean);
            }
        });
    }

    private Object newInstance(Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("实例化失败");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("实例化失败-非法的访问权限");
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求url
        String requestUrl = req.getRequestURI();
        //2.查找urlHandler
        UrlHandlerSupport support = urlMappings.get(requestUrl);
        if (support == null) {
            resp.getWriter().write("not found url 404");
        }
        String methodReturn = "";
        try {
            methodReturn = support.getMethod().invoke(support.getTarget()).toString();
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.视图转化器
        viewResover(methodReturn, req, resp);
    }

    private void viewResover(String viewName, HttpServletRequest req, HttpServletResponse resp) {
        String suffix = "";
        String preffix = "";
//        try {
////            req.getRequestDispatcher(preffix + viewName + suffix).forward(req, resp);
//        } catch (ServletException | IOException e) {
//            e.printStackTrace();
//        }
        try {
            resp.getWriter().write(viewName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
