package co.nastooh.transactions;

import co.nastooh.tables.Daily;
import co.nastooh.tables.Stock;
import co.nastooh.tables.Trade;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.ArrayList;

public class InsertOrUpdateDailies {

    public static void run(ArrayList<Daily> dailyList){

        // connecting to the database:
        Configuration con = new Configuration().configure()
                .addAnnotatedClass(Daily.class)
                .addAnnotatedClass(Stock.class)
                .addAnnotatedClass(Trade.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();


        // inserting or updating dailies:
        for (Daily daily : dailyList){
            SessionFactory sf = con.buildSessionFactory(reg);
            Session session = sf.openSession();
            session.beginTransaction();
            session.saveOrUpdate(daily);
            session.getTransaction().commit();
            session.close();
            sf.close();
        }



        System.out.println("Dailies of "+dailyList.get(0).getStock().getId()+" has been inserted/updated successfully");
    }

    public static void insertOneDaily(Daily daily){

        // connecting to the database:
        Configuration con = new Configuration().configure()
                .addAnnotatedClass(Daily.class)
                .addAnnotatedClass(Stock.class)
                .addAnnotatedClass(Trade.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();


        // creating a session:
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        session.beginTransaction();
        // inserting daily:
        session.saveOrUpdate(daily);
        // disconnecting:
        session.getTransaction().commit();
        session.close();
        sf.close();

    }
}
