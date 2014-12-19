package com.golenok.lab_1_b.application_services;

/**
 * Created by Stacy on 18.12.2014.
 */


import com.golenok.lab_1_b.domain.Book;
import com.golenok.lab_1_b.domain.RepositoryBooks;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

//import javax.persistence.Query;

//import javax.persistence.Query;

//import org.mockito.Mockito;

public class ApplicationServiceImplTestIntegration {

    private SessionFactory session;
    private ApplicationService service;
    private RepositoryBooks repository;

    @Before
    public void setUp() throws IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("application_context.xml");
        session = (SessionFactory) context.getBean("sessionFactory");
        service = (ApplicationService) context.getBean("applicationServices");
        repository = (RepositoryBooks) context.getBean("repository");
        if (repository.getAllBooks().size() > 0) {
            clearData();
        }


    }

    private void clearData() {
        Transaction tr = session.getCurrentSession().beginTransaction();
        Query query = session.getCurrentSession().createQuery("DELETE FROM Book");
        query.executeUpdate();
        tr.commit();
    }


    private void initDBNotK() {

        Book book1 = new Book(1, "Nnn", "Stacy");
        Book book2 = new Book(2, "Nnn", "Stacy");
        Book book3 = new Book(5, "fd", "Stacy");
        Book book4 = new Book(8, "LF8", "Stacy");
        Book book5 = new Book(13, "GH", "Stacy");
        Book book6 = new Book(21, "EF", "Stacy");

        Transaction tr = session.getCurrentSession().beginTransaction();
        session.getCurrentSession().save(book1);
        session.getCurrentSession().save(book2);
        session.getCurrentSession().save(book3);
        session.getCurrentSession().save(book4);
        session.getCurrentSession().save(book5);
        session.getCurrentSession().save(book6);

        tr.commit();
    }


    private void initDBWithK() {

        Book book1 = new Book(1, "Knn", "Stacy");
        Book book2 = new Book(2, "Fff", "Stacy");
        Book book3 = new Book(5, "Kvv", "Stacy");
        Book book4 = new Book(6, "Kng", "Stacy");
        Book book5 = new Book(8, "Rdv", "Stacy");
        Book book6 = new Book(11, "KFde", "Stacy");
        Book book7 = new Book(12, "Fde", "Stacy");

        Transaction tr = session.getCurrentSession().beginTransaction();
        session.getCurrentSession().save(book1);
        session.getCurrentSession().save(book2);
        session.getCurrentSession().save(book3);
        session.getCurrentSession().save(book4);
        session.getCurrentSession().save(book5);
        session.getCurrentSession().save(book6);
        session.getCurrentSession().save(book7);


        tr.commit();
    }

    // удалить все с именем К
    @Test

    public void testDelEntitiesStartNameWithK() throws Exception {

        initDBWithK();
        List<Book> expResult = repository.getAllBooks();

        expResult.remove(5);
        expResult.remove(3);
        expResult.remove(2);
        expResult.remove(0);


        service.delEntitiesStartNameWithK();

        List<Book> result = repository.getAllBooks();
        for (int i = 0; i < expResult.size(); i++) {
            assertEquals(expResult.get(i), result.get(i));
        }

    }

    // удалить все с именем К (если их нет)
    @Test

    public void testDelEntitiesStartNameWithKWhenNothingDel() throws Exception {

        initDBNotK();

        List<Book> expResult = repository.getAllBooks();

        service.delEntitiesStartNameWithK();

        List<Book> result = repository.getAllBooks();

        for (int i = 0; i < expResult.size(); i++) {
            assertEquals(expResult.get(i), result.get(i));
        }

    }


    // удалить все с id не число Фиб.
    @Test

    public void testDelEntitiesWhereIdNoFibo() throws Exception {

        initDBWithK();

        List<Book> expResult = repository.getAllBooks();

        expResult.remove(6);
        expResult.remove(5);
        expResult.remove(3);

        service.delEntitiesWhereIdNoFibo();

        List<Book> result = repository.getAllBooks();
        for (int i = 0; i < expResult.size(); i++) {
            assertEquals(expResult.get(i), result.get(i));
        }

    }

    // удалить все id не Фиб. (если их нет)
    @Test

    public void testDelEntitiesWhereIdNoFiboWhenNothingDel() throws Exception {

        initDBNotK();

        List<Book> expResult = repository.getAllBooks();

        service.delEntitiesWhereIdNoFibo();

        List<Book> result = repository.getAllBooks();
        for (int i = 0; i < expResult.size(); i++) {
            assertEquals(expResult.get(i), result.get(i));
        }

    }


}

