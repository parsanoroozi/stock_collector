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
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        session.beginTransaction();

        // getting the last added query
        Query query = session.createQuery("from Trade order by id DESC");
        query.setMaxResults(1);
        Trade lastTrade = (Trade) query.uniqueResult();

        session.getTransaction().commit();
        session.close();
        sf.close();

        return lastTrade;
    }

    public static void run(ArrayList<Trade> tradeList){

        // connecting to the database:
        Configuration con = new Configuration().configure()
                .addAnnotatedClass(Trade.class)
                .addAnnotatedClass(Daily.class)
                .addAnnotatedClass(Stock.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();


        // inserting or updating trades:
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        session.beginTransaction();
        System.out.println("trade list size : " + tradeList.size());
        for (Trade trade : tradeList){
            session.saveOrUpdate(trade);
        }

        session.getTransaction().commit();
        session.close();
        sf.close();


        if(tradeList.size()>0){
            System.out.println("trades of "+tradeList.get(0).getDaily().getStock().getId()
                    +" in "+ tradeList.get(0).getDaily().getDate() +" has been inserted/updated successfully");
        }
    }
}
