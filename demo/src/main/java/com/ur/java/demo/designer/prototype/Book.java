package com.ur.java.demo.designer.prototype;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class Book implements Cloneable {
    private String title;
    private int totalPage;
    private ArrayList<String> image = new ArrayList<>();

    public void setImage(String img) {
        image.add(img);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public ArrayList<String> getImage() {
        return image;
    }

    public void setImage(ArrayList<String> image) {
        this.image = image;
    }

    public void show() {
        log.info("title:" + title);
        log.info("image:");
        for (String str : this.image) {
            log.info(str);
        }
        log.info("totalPage:" + totalPage);
    }

    /**
     * 1.只拷贝值类型，不拷贝引用类型；
     * 2.引用类型拷贝之后还是共同的引用；
     *
     * @return
     * @throws CloneNotSupportedException
     */
    Book cloneBook() throws CloneNotSupportedException {
        Book t = (Book) this.clone();
        //***深度拷贝***
        t.image = (ArrayList<String>) this.image.clone();
        return t;
    }
}
