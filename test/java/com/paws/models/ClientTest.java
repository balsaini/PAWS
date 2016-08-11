package com.paws.models;

import com.paws.util.Mysql;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 8/10/16.
 */
public class ClientTest {

    @Before
    public void setUp() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        session.createNativeQuery("truncate table clients").executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateNewClient() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Client c = new Client("jennifer");
        session.save(c);
        session.getTransaction().commit();
        session.close();
    }

    @Test(expected = org.hibernate.exception.DataException.class)
    public void shouldNotSaveDueToNameTooLong() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Client c = new Client("Richaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        session.save(c);
        session.getTransaction().commit();
        session.close();
    }
}