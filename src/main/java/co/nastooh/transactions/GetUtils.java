package co.nastooh.transactions;


import co.nastooh.tables.UtilsTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.time.Instant;

public class GetUtils {

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

        // get the utils instance:
        UtilsTable utilsInstance = (UtilsTable) session.get(UtilsTable.class,1);
        session.close();
        sf.close();

        return utilsInstance.getLast_daily_update();
    }

}
