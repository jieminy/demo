package com.ur.java.demo.designer.builder;

public class Director {
    public static Person buildPerson(PersonBuilder builder) {
        builder.buildHead();
        builder.buildBody();
        builder.buildFoot();
        return builder.buildPerson();
    }
}
