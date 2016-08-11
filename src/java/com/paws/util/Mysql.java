package com.paws.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Mysql {
    private static SessionFactory sessionFactory;

    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
//                .applySetting("hibernate.connection.username", System.getenv("MYSQL_USER"))
//                .applySetting("hibernate.connection.password", System.getenv("MYSQL_PASS"))
                .applySetting("hibernate.connection.username", "root")
                .applySetting("hibernate.connection.password", "pass@word1")
                .build();

        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.out.println("Unable to connect.");
            System.out.println("Error Message: " + e.toString());
            System.out.println("Cause: " + e.getCause());
            System.out.println("Message: " + e.getMessage());
            StackTraceElement[] stackTrace = e.getStackTrace();
            for(byte i = 0; i < stackTrace.length; i++) {
                System.out.println("Traceline: " + stackTrace[i].toString());
            }
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    public static Session getSession() throws HibernateException {
        System.out.println("Username was: " + System.getenv("MYSQL_USER"));
        System.out.println("Password was: " + System.getenv("MYSQL_PASS"));
        return sessionFactory.openSession();
    }
}