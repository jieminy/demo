package com.ur.java.demo.designer.builder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuilderMain {
    public static void main(String[] args) {
        PersonBuilder personBuilder = new AmericaPersonBuilder();
        Person person = Director.buildPerson(personBuilder);
        log.info(person.toString());
    }
}
