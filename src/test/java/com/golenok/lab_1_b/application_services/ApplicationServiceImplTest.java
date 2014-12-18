/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golenok.lab_1_b.application_services;

import com.golenok.lab_1_b.domain.Book;
import com.golenok.lab_1_b.domain.RepositoryBooks;
import com.golenok.lab_1_b.domain.RepositoryBooksImpl;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
//import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 *
 * @author Stacy
 */
public class ApplicationServiceImplTest {

    public ApplicationServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    protected ArrayList<Book> initMockRepositoryBooksNotK(RepositoryBooks DB_Book) {

        ArrayList<Book> listBook = new ArrayList<Book>();

        listBook.add(new Book(1, "Nnn", "ivanov"));
        listBook.add(new Book(2, "Fff", "Petrov"));
        listBook.add(new Book(5, "Rdv", "Ivanov"));
        listBook.add(new Book(8, "Vvv", "Petrov"));
        listBook.add(new Book(13, "Kng", "Ivanov"));
        listBook.add(new Book(21, "FFde", "Petrov"));
        when(DB_Book.getAllBooks()).thenReturn(listBook);
        return listBook;
    }

    protected ArrayList<Book> initMockRepositoryBooksWithK(RepositoryBooks DB_Book) {

        ArrayList<Book> listBook = new ArrayList<Book>();

        listBook.add(new Book(1, "Knn", "ivanov"));
        listBook.add(new Book(2, "Fff", "Petrov"));
        listBook.add(new Book(8, "Rdv", "Ivanov"));
        listBook.add(new Book(5, "Kvv", "Petrov"));
        listBook.add(new Book(6, "Kng", "Ivanov"));
        listBook.add(new Book(11, "KFde", "Petrov"));
        when(DB_Book.getAllBooks()).thenReturn(listBook);
        return listBook;
    }

    /**
     * Test of accessoryIdToFibo method, of class ApplicationServiceImpl.
     */
    @Test
    public void testAccessoryIdToFiboT() {

        long id = 8;
        ApplicationServiceImpl instance = new ApplicationServiceImpl();
        boolean expResult = true;
        boolean result = instance.accessoryIdToFibo(id);
        assertEquals(expResult, result);
    }

    @Test
    public void testAccessoryIdToFiboF() {

        long id = 10;
        ApplicationServiceImpl instance = new ApplicationServiceImpl();
        boolean expResult = false;
        boolean result = instance.accessoryIdToFibo(id);
        assertEquals(expResult, result);

    }

    //  getAllBooks ����� ������ 1 ��� ��� delEntitiesStartNameWithK()
    @Test
    public void testGetAllBooksCalledIndelByName() {

        RepositoryBooks repository = mock(RepositoryBooksImpl.class);
        ApplicationServiceImpl instance = new ApplicationServiceImpl();
        initMockRepositoryBooksWithK(repository);
        instance.setDB_books(repository);
        instance.delEntitiesStartNameWithK();
        verify(repository, times(1)).getAllBooks();
    }

    //  getAllBooks ����� ������ 1 ��� ��� delEntitiesWhereIdNoFibo()
    @Test
    public void testGetAllBooksCalledIndelById() {

        RepositoryBooks repository = mock(RepositoryBooksImpl.class);
        ApplicationServiceImpl instance = new ApplicationServiceImpl();
        initMockRepositoryBooksWithK(repository);
        instance.setDB_books(repository);
        instance.delEntitiesWhereIdNoFibo();
        verify(repository, times(1)).getAllBooks();
    }

    //  delEntity() �� ���������� � delEntitiesStartNameWithK() 
    @Test
    public void testDelEntityNotCallInDelByName() {
        RepositoryBooks repository = mock(RepositoryBooksImpl.class);
        ApplicationServiceImpl instance = new ApplicationServiceImpl();
        initMockRepositoryBooksNotK(repository);
        instance.setDB_books(repository);
        instance.delEntitiesStartNameWithK();

        Mockito.verify(repository, times(0)).delEntity(null);
    }

//  delEntity() �� ���������� �    delEntitiesWhereIdNoFibo();
    @Test
    public void testDelEntityNotCallInDelById() {
        RepositoryBooks repository = mock(RepositoryBooksImpl.class);
        ApplicationServiceImpl instance = new ApplicationServiceImpl();
        initMockRepositoryBooksNotK(repository);
        instance.setDB_books(repository);
        instance.delEntitiesWhereIdNoFibo();

        Mockito.verify(repository, times(0)).delEntity(null);
    }
// ����� ������ delEntity() 4 ����  delEntitiesStartNameWithK() 

    @Test
    public void testDelEntityCallRepeatedlyDelByName() {
        RepositoryBooks repository = mock(RepositoryBooksImpl.class);
        ApplicationServiceImpl instance = new ApplicationServiceImpl();
        initMockRepositoryBooksWithK(repository);
        instance.setDB_books(repository);
        instance.delEntitiesStartNameWithK();
    Mockito.verify(repository, times(1)).getAllBooks();
        Mockito.verify(repository, Mockito.atMost(4)).delEntity((Book) Mockito.anyObject());

    }

 //����� ������ delEntity() 2 ���� delEntitiesWhereIdNoFibo()
    @Test
    public void testDelEntityCallRepeatedlyDelById() {
        RepositoryBooks repository = mock(RepositoryBooksImpl.class);
        ApplicationServiceImpl instance = new ApplicationServiceImpl();
        initMockRepositoryBooksWithK(repository);
        instance.setDB_books(repository);
        instance.delEntitiesWhereIdNoFibo();
    Mockito.verify(repository, times(1)).getAllBooks();
        Mockito.verify(repository, Mockito.atMost(2)).delEntity((Book) Mockito.anyObject());

    }
   
}
