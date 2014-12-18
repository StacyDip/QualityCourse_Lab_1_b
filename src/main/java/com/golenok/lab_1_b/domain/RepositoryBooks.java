/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golenok.lab_1_b.domain;

import java.util.List;

public interface RepositoryBooks {

    public List<Book> getAllBooks();

    public void delEntity(Book entity);

}
