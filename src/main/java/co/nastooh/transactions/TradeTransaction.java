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

public class TradeTransaction {

    public static void run(ArrayList<Trade> tradeList){

        // connecting to the database:
        Configuration con = new Configuration().configure()
                .addAnnotatedClass(Trade.class)
                .addAnnotatedClass(Daily.class)
                .addAnnotatedClass(Stock.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();


        // inserting or updating trades:
        for (Trade trade : tradeList){
            SessionFactory sf = con.buildSessionFactory(reg);
            Session session = sf.openSession();
            session.beginTransaction();
            session.saveOrUpdate(trade);
            session.getTransaction().commit();
            session.close();
            sf.close();
        }




        if(tradeList.size()>0){
            System.out.println("trades of "+tradeList.get(0).getDaily().getStock().getId()
                    +" in "+ tradeList.get(0).getDaily().getDate() +"has been inserted/updated successfully");
        }
    }
}
