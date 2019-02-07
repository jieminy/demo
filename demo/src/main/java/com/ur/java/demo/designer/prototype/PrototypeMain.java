package com.ur.java.demo.designer.prototype;

public class PrototypeMain {
    public static void main(String[] args) throws CloneNotSupportedException {
        Book book = new Book();
        book.setTitle("哈利波特");
        book.setTotalPage(1000);
        book.setImage("卡拉卡拉.img");
        book.show();

        Book book1 = book.cloneBook();
        book1.setTitle("岛上书店");
        book1.setTotalPage(500);
        book1.setImage("小仙女.img");
        book1.show();

        book.show();
    }
}
