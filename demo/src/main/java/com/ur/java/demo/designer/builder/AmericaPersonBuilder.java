package com.ur.java.demo.designer.builder;

public class AmericaPersonBuilder implements PersonBuilder {
    private Person person;

    public AmericaPersonBuilder() {
        this.person = new Person();
    }

    @Override
    public void buildHead() {
        person.setHead("蓝眼睛");
    }

    @Override
    public void buildBody() {
        person.setBody("大块头");
    }

    @Override
    public void buildFoot() {
        person.setFoot("大长腿");
    }

    @Override
    public Person buildPerson() {
        return person;
    }
}
