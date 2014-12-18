/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golenok.lab_1_b.domain;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Stacy
 */
public class RepositoryBooksImpl implements RepositoryBooks {

    private SessionFactory session;

    public SessionFactory getSession() {
        return session;
    }

    public void setSession(SessionFactory session) {
        this.session = session;
    }

    @Override
    public List<Book> getAllBooks() {

        Session currentSession = session.getCurrentSession();
        Transaction tr = session.getCurrentSession().beginTransaction();
        List<Book> listBooks = currentSession.createCriteria(Book.class).list();
        tr.commit();
        return listBooks;
    }

    @Override
    public void delEntity(Book entity) {

        Transaction tr = session.getCurrentSession().beginTransaction();
        Session currentSession = session.getCurrentSession();
        currentSession.delete(entity);
        tr.commit();
    }

}
