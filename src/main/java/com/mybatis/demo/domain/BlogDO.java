package com.mybatis.demo.domain;

import java.io.Serializable;

/**
 * @author: heartaway
 * @create: 2018-04-02 下午11:43
 */
public class BlogDO implements Serializable {

    private String title;

    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
