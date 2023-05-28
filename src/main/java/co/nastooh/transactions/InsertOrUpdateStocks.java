package co.nastooh.transactions;

import co.nastooh.tables.Stock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.ArrayList;

public class InsertOrUpdateStocks {
    public static void run(ArrayList<Stock> stocks){

        // connecting to the database:
        Configuration con = new Configuration().configure().addAnnotatedClass(Stock.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        session.beginTransaction();

        // inserting stocks:
        for (Stock stock : stocks){
            session.saveOrUpdate(stock);
        }

        session.getTransaction().commit();
        session.close();

        System.out.println("Stocks has been inserted/updated successfully");
    }
}
