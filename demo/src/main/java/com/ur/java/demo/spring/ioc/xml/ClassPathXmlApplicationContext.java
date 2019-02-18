package com.ur.java.demo.spring.ioc.xml;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 自定义spring容器
 */
@Slf4j
public class ClassPathXmlApplicationContext {
    private String xmlUrl;

    public ClassPathXmlApplicationContext(String xmlUrl) {
        this.xmlUrl = xmlUrl;
    }

    /**
     * 读取xml
     *
     * @return 所有element
     */
    private List read() {
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(getResourceAsInputStream());
        } catch (DocumentException e) {
            throw new RuntimeException(e.getMessage());
        }
        return document.getRootElement().elements();
    }

    /**
     * 根据id获取检索element
     *
     * @param elements 所有节点
     * @param id       欲查询element的id
     * @return 欲检索的element
     */
    private Element getElementById(List<Element> elements, String id) {
        for (Element element : elements) {
            Attribute attribute = element.attribute("id");
            if (attribute != null && attribute.getValue().equals(id)) {
                return element;
            }
        }
        return null;
    }

    /**
     * 获取xml文件流
     *
     * @return 文件流
     */
    private InputStream getResourceAsInputStream() {
        return this.getClass().getClassLoader().getResourceAsStream(xmlUrl);
    }

    /**
     * 根据id 获取容器中的对象
     *
     * @param id id
     * @return 实例对象
     * @throws DocumentException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Object getBean(String id) throws DocumentException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (StringUtils.isBlank(id)) {
            throw new RuntimeException("id不能为空！");
        }
        //返回实例
        return newBean(id);
    }

    /**
     * 获取配置文件中classUrl
     *
     * @param element
     * @return
     */
    private String getClassUrl(Element element) {
        return element.attribute("class").getValue();
    }

    private String getInitMethod(Element element) {
        Attribute attribute = element.attribute("init-method");
        if (attribute != null) {
            return attribute.getValue();
        }
        return "";
    }

    /**
     * 3.根据反射实例化对象
     *
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Object newBean(String id) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Element element = getElementById(read(), id);
        assert element != null;
        //读取配置文件bean全路径
        String classUrl = getClassUrl(element);
        if (StringUtils.isBlank(classUrl)) {
            throw new RuntimeException("class获取失败，请检查配置");
        }
        Class<?> clazz = Class.forName(classUrl);
        Object target = clazz.newInstance();
        String initMethod = getInitMethod(element);
        if (StringUtils.isNotBlank(initMethod)) {
            Method method = clazz.getDeclaredMethod(initMethod, null);
            method.invoke(target, null);
        }
        return target;
    }

}
