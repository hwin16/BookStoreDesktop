package admin; /**
 * Created by sudo on 5/26/16.
 */
import datamodels.People;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class ManagePeople {
    private static SessionFactory factory;

    public static void main(String[] args){
        factory = new Configuration().configure().buildSessionFactory();
        ManagePeople bookstore = new ManagePeople();
        Integer p1 = bookstore.addPeople("Htut Khine", "Htay Win");

        bookstore.listPeople();
        System.out.print("done.");
        //bookstore.updatePeople(p1, "Win Htay");
        //bookstore.deletePeople(p1);
        //bookstore.listPeople();
    }

    public Integer addPeople(String firstname, String lastname){
        Session session = factory.openSession();
        Transaction t = null;
        Integer pId = null;
        t = session.beginTransaction();
        People people = new People(firstname, lastname);
        pId = (Integer) session.save(people);
        t.commit();
        session.close();
        return pId;
    }

    public void listPeople() {
        Session session = factory.openSession();
        Transaction t = null;
        t = session.beginTransaction();
        List p = session.createQuery("FROM People").list();
        for (Iterator i = p.iterator(); i.hasNext(); ){
            People people = (People) i.next();
            System.out.print("First Name: " + people.getFirstname() + " ");
            System.out.print("Last Name: " + people.getLastname());
            System.out.println();
        }
        t.commit();
        session.close();
    }
}