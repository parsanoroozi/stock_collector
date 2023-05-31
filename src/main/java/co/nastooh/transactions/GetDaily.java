package co.nastooh.transactions;

import co.nastooh.tables.Daily;
import co.nastooh.tables.UtilsTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class GetDaily {

    public static Boolean checkIfExists(Daily daily){

        // initiating daily object:
        Daily retrievedDaily = new Daily();
        retrievedDaily.setId("");

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
        return retrievedDaily.getId().length() > 0;
    }
}
