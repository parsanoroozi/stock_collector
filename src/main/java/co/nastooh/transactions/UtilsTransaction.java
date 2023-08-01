package co.nastooh.transactions;


import co.nastooh.tables.UtilsTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.time.Instant;

public class UtilsTransaction {

    public static boolean isHistoryOver(){

        // connecting to the database:
        Configuration con = new Configuration().configure().addAnnotatedClass(UtilsTable.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();

        // temporary only first time adding:
        session.beginTransaction();
        UtilsTable utils = new UtilsTable();
        utils.setHistory_finished(false);
        utils.setLast_daily_update(Instant.now().getEpochSecond());
        session.saveOrUpdate(utils);
        session.getTransaction().commit();
        // temporary syntax for creating the table of utils...


        // get the utils instance:
        UtilsTable utilsInstance = (UtilsTable) session.get(UtilsTable.class,0);
        session.close();

        return utilsInstance.isHistory_finished();
    }

    public static long lastDetailedUpdate(){

        // connecting to the database:
        Configuration con = new Configuration().configure().addAnnotatedClass(UtilsTable.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();

//        UtilsTable utils = new UtilsTable();
//        utils.setHistory_finished(false);
//        utils.setLast_daily_update(0L);
//        session.beginTransaction();
//        session.save(utils);
//        session.beginTransaction().commit();

        // get the utils instance:
        UtilsTable utilsInstance = (UtilsTable) session.get(UtilsTable.class,0);
        session.close();
        sf.close();
        return utilsInstance.getLast_daily_update();
    }

    public static void saveNewUpdateDate(long lastUpdate){

        // connecting to the database:
        Configuration con = new Configuration().configure().addAnnotatedClass(UtilsTable.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();

        // get the utils instance:
        UtilsTable utilsInstance = (UtilsTable) session.get(UtilsTable.class,0);
        // update last update:
        utilsInstance.setLast_daily_update(lastUpdate);
        session.beginTransaction();
        session.update(utilsInstance);
        session.getTransaction().commit();

        session.close();
        sf.close();
    }
}
