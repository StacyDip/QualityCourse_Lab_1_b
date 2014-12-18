/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golenok.lab_1_b.domain;

/**
 *
 * @author Stacy
 */
public class Book {
    
    private long id;
    private String name;
    private String author;

    public Book(long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;

        if(obj instanceof Book){
            Book book2 = (Book) obj;
            isEqual = ((id==book2.id) && (name.equals(book2.name)) && (author.equals(book2.author)));
        }

        return isEqual;
    }
}
