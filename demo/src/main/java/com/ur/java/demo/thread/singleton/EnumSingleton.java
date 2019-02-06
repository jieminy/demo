package com.ur.java.demo.thread.singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnumSingleton {
    private static EnumSingleton instance;

    private EnumSingleton(){}

    public static EnumSingleton getInstance(){
        return Inner.INSTANCCE.getSingleton();
    }

    enum Inner{
        INSTANCCE;
        Inner(){
            this.singleton = new EnumSingleton();
        }
        private EnumSingleton singleton;

        public EnumSingleton getSingleton() {
            return singleton;
        }
    }

    public static void main(String[] args){
        EnumSingleton singleton1 = EnumSingleton.getInstance();
        EnumSingleton singleton2 = EnumSingleton.getInstance();
        log.info((singleton1 == singleton2) +"");
    }
}
