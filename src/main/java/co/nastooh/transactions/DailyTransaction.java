package co.nastooh.transactions;

import co.nastooh.tables.Daily;
import co.nastooh.tables.Stock;
import co.nastooh.tables.Trade;
import co.nastooh.tables.UtilsTable;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.ArrayList;

public class DailyTransaction {

    public static Boolean checkIfExists(Daily daily){

        // initiating daily object:
        Daily retrievedDaily = new Daily();

        // connecting to the database:
        Configuration con = new Configuration().configure().addAnnotatedClass(UtilsTable.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();

        // get daily by id if exists:
        retrievedDaily = (Daily) session.get(Daily.class, daily.getId());
        session.close();
        sf.close();

        // return true if daily exists or false if not:
        return Long.toString(retrievedDaily.getId()).length() > 0;
    }

    public static Daily getLastDailyAdded(){

        // connecting to the database:
        Configuration con = new Configuration().configure().addAnnotatedClass(UtilsTable.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        session.beginTransaction();

        // getting the last added query
        Query query = session.createQuery("from Daily order by id DESC");
        query.setMaxResults(1);
        Daily lastDaily = (Daily) query.uniqueResult();

        session.getTransaction().commit();
        session.close();
        sf.close();

        return lastDaily;
    }

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