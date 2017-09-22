package com.cards.shvedko.Services;

import com.cards.shvedko.Model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DBService {

    public static SessionFactory sessionFactory;
    private static StandardServiceRegistry serviceRegistry;

    public DBService(){
        Configuration configuration = getSQLiteConfiguration();
        sessionFactory = createSessionFactory();
    }

    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration
                .addPackage("com.cards.shvedko.Model")
                .addAnnotatedClass(Cards.class)
                .addAnnotatedClass(CardCategories.class)
                .addAnnotatedClass(CardFiles.class)
                .addAnnotatedClass(CardFilesDe.class)
                .addAnnotatedClass(CardLanguageDe.class)
                .addAnnotatedClass(CardLanguages.class)
                .addAnnotatedClass(CardTypes.class)
                .addAnnotatedClass(SystemConfigs.class)
                .addAnnotatedClass(Users.class)
                .addAnnotatedClass(CardsPrepositionDativ.class)
                .addAnnotatedClass(CardsPrepositionAkkusativ.class)
                .buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    private Configuration getSQLiteConfiguration() {
        Configuration configuration = new Configuration();
        return configuration;
    }
}
