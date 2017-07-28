package com.cards.shvedko;

import com.cards.shvedko.Model.Cards;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainApp {
    private static org.hibernate.service.ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;


    public static void main(String[] args) {
        MainApp.createSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction t = session.beginTransaction();

        Cards e1 = new Cards();
        e1.setId(1);
        e1.setName("test");
        e1.setValue("bezahlen");
        e1.setExample("bezahlen");
        e1.setTypeId(1);
        e1.setCategoryId(1);
        e1.setIsVisible(1);

        session.persist(e1);

        t.commit();

        List<Cards> cardList = session.createQuery("from Cards").list();

        for (Cards card : cardList) {
            System.out.println(card.getValue());
        }

        session.close();
        System.out.println("successfully saved");
    }

    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
}
