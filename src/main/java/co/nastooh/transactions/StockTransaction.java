package co.nastooh.transactions;

import co.nastooh.crawlers.StocksCrawler;
import co.nastooh.engines.realtime_engine.RealTimeCollector;
import co.nastooh.tables.Daily;
import co.nastooh.tables.Stock;
import co.nastooh.tables.Trade;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.ArrayList;

public class StockTransaction {

    private static Session openningSession(){
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
//                sf.close();
            }
        }
        return session;
    }
    public static void insert(ArrayList<Stock> stockList){

        // connecting to the database:
        Session session = openningSession();
        // inserting or updating stocks:
        for (Stock stock : stockList){
            session.saveOrUpdate(stock);
        }
        session.getTransaction().commit();
        session.close();

        System.out.println("Stocks has been inserted/updated successfully");
    }

    private static ArrayList<Stock> getStockListInner(Session session){
        // getting stock list:
        Query query = session.createQuery("from Stock order by id ASC");
        return new ArrayList<Stock>(query.list());
    }

    public static ArrayList<Stock> getStockList(){

        // connecting to the database:
        Session session = openningSession();

        // getting stock list:
        ArrayList<Stock> stockList = getStockListInner(session);
        // closing the extra opened session:
        session.getTransaction().commit();
        session.close();

        // check if we have fetched stocks for the first time:
        if(stockList.size() == 0){
            // fetching stocks:
            stockList = StocksCrawler.collectStocks();

//            sf.close();
            //  inserting stocks into the database or updating them if they already exist:
            StockTransaction.insert(stockList);
            // getting stocks:
            session = openningSession();
            stockList = getStockListInner(session);
            // closing the connection:
            session.getTransaction().commit();
            session.close();
        }

        return stockList;
    }
}
