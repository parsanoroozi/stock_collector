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
import java.util.Arrays;

public class TradeTransaction {

    public static Trade getLastTradeAdded(){
        // connecting to the database:
        Configuration con = new Configuration().configure()
                .addAnnotatedClass(Stock.class)
                .addAnnotatedClass(Daily.class)
                .addAnnotatedClass(Trade.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();

        // defining exception flag and session variables:
        boolean DBConnectionException = true;
        SessionFactory sf = null;
        Session session = null;
        // while there is a "too many Connections Exception":
        while (DBConnectionException){
            try{
                // opening a new session:
                sf = con.buildSessionFactory(reg);
                session = sf.openSession();
                session.beginTransaction();
                DBConnectionException = false;
            }catch (Exception e){
                System.out.println("too many Connections exception");
                session.close();
                sf.close();
            }
        }

        // getting the last added query
        Query query = session.createQuery("from Trade order by id DESC");
        query.setMaxResults(1);
        Trade lastTrade = (Trade) query.uniqueResult();

        // committing and closing the session and connection:
        session.getTransaction().commit();
        session.close();
        sf.close();

        return lastTrade;
    }

    public static void insert(ArrayList<Trade> tradeList){
        // connecting to the database:
        Configuration con = new Configuration().configure()
                .addAnnotatedClass(Trade.class)
                .addAnnotatedClass(Daily.class)
                .addAnnotatedClass(Stock.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();

        // defining exception flag and session variables:
        System.out.println("trade list size : " + tradeList.size());
        boolean DBConnectionException = true;
        SessionFactory sf = null;
        Session session = null;
        // while there is a "too many Connections Exception":
        while (DBConnectionException){
            try{
                // opening a new session:
                sf = con.buildSessionFactory(reg);
                session = sf.openSession();
                session.beginTransaction();
                DBConnectionException = false;
            }catch (Exception e){
                System.out.println("too many Connections exception");
                session.close();
                sf.close();
            }
        }
        // inserting or updating trades:
        for (Trade trade : tradeList){
            session.saveOrUpdate(trade);
        }

        session.getTransaction().commit();
        session.close();
        sf.close();

        // information logs:
        if(tradeList.size()>0){
            System.out.println("trades of "+tradeList.get(0).getDaily().getStock().getId()
                    +" in "+ tradeList.get(0).getDaily().getDate()
                    +" has been inserted/updated successfully");
            System.out.println();
        }
    }
}
