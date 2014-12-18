/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golenok.lab_1_b.application_services;

import com.golenok.lab_1_b.domain.Book;
import com.golenok.lab_1_b.domain.RepositoryBooks;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Stacy
 */
public class ApplicationServiceImpl implements ApplicationService {

    RepositoryBooks DB_books;

    public RepositoryBooks getDB_books() {
        return DB_books;
    }

    public void setDB_books(RepositoryBooks DB_books) {
        this.DB_books = DB_books;
    }

    @Override
    @Transactional
    public void delEntitiesStartNameWithK() {
        List<Book> listBook = DB_books.getAllBooks();
        for (Book book : listBook) {
            String name = book.getName();
            if (name.charAt(0) == 'K') {
                DB_books.delEntity(book);

            }
        }
    }

    @Transactional
    @Override
    public void delEntitiesWhereIdNoFibo() {


        List<Book> listBook = DB_books.getAllBooks();
        for (Book book : listBook) {
            long id = book.getId();
            boolean idFiboNum = accessoryIdToFibo(id);
            if (idFiboNum == false) {
                DB_books.delEntity(book);
            }
        }

    }

    public boolean accessoryIdToFibo(long id) {

        int first = 1;
        int second = 1;
        int fibo = 1;
        if (id == 1) {
            return true;
        } else {
            for (int i = 0; i <= id; i++) {
                fibo = first + second;
                first = second;
                second = fibo;
                if (fibo == id) {
                    return true;
                }
            }
        }

        return false;
    }
}
