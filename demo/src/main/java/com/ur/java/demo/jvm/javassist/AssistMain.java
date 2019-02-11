package com.ur.java.demo.jvm.javassist;

import javassist.*;

import java.io.IOException;

/**
 * java字节码技术
 * 通过字节码创建类
 */
public class AssistMain {
    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException {
        ClassPool pool = ClassPool.getDefault();

        //1.创建user类
        CtClass userClass = pool.makeClass("User");
        //2.创建name,age属性
        CtField nameField = CtField.make("  private String name;", userClass);
        CtField ageField = CtField.make("   private Integer age;", userClass);
        //3.添加属性
        userClass.addField(nameField);
        userClass.addField(ageField);
        //4.创建构造方法头
//        CtConstructor ctConstructor = new CtConstructor(
//                new CtClass[] {pool.get("java.lang.String"), pool.get("java.lang.Integer")},
//                userClass
//        );
//        //5.创建构造方法body
//        ctConstructor.setBody("{this.name = name; this.age = age;}");
//        //6.添加构造方法
//        userClass.addConstructor(ctConstructor);
//        //7.创建getName()方法
//        CtMethod getNameMethod = CtMethod.make("public String getName(){return name;}", userClass);
//        userClass.addMethod(getNameMethod);

        //生成class文件
        userClass.writeFile("D:/");
    }
}
