package com.paws.models;
import com.paws.util.Mysql;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by localadmin on 8/10/16.
 */
public class ShelterTest {
    @Before
    public void setUp() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        session.createNativeQuery("truncate table shelters").executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateNewShelter() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Shelter s = new Shelter("SomeShelter");
        session.save(s);
        session.getTransaction().commit();
        session.close();
    }

    @Test(expected = org.hibernate.exception.DataException.class)
    public void shouldNotSaveShelterDueToNameTooLong() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Shelter s = new Shelter("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        session.save(s);
        session.getTransaction().commit();
        session.close();
    }

    @Test (expected = org.hibernate.exception.ConstraintViolationException.class)
    public void shouldNotSaveShelterDueToNameNotUnique() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Shelter s1 = new Shelter("SomeDumbShelter");
        Shelter s2 = new Shelter("SomeDumbShelter");
        session.save(s1);
        session.getTransaction().commit();
        session.save(s2);
        session.getTransaction().commit();
        session.close();
    }
}
