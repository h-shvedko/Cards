package com.cards.shvedko.Services;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBService {

    public static org.hibernate.service.ServiceRegistry serviceRegistry;
    public static SessionFactory sessionFactory;

    public DBService(){
        Configuration configuration = getSQLiteConfiguration();
        sessionFactory = createSessionFactory();
    }

    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    private Configuration getSQLiteConfiguration() {
        Configuration configuration = new Configuration();
        return configuration;
    }
}
