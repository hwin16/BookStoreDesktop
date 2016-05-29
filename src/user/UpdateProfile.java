package user;

import datamodels.People;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by sudo on 5/27/16.
 */
public class UpdateProfile {
    public UpdateProfile() {}
    private SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public void showCurrentProfile(long people_id) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        People user = (People) session.createQuery("From People where people_id = :people_id").setParameter("people_id", people_id).uniqueResult();
        if (user != null) {
            System.out.println("1. FirstName: " + user.getFirstname());
            System.out.println("2. MiddleName: " + user.getMiddlename());
            System.out.println("3. LastName: " + user.getLastname());
            System.out.println("4. Birthdate: " + user.getBirthdate());
            System.out.println("5. AddressLine1: " + user.getAddressline1());
            System.out.println("6. AddressLine2: " + user.getAddressline2());
            System.out.println("7. AddressLine3: " + user.getAddressline3());
            System.out.println("8. City: " + user.getCity());
            System.out.println("9. Zip: " + user.getZip());
            System.out.println("10. State: " + user.getZip());
        }
        else {
            System.out.println("We don't have your user profile.");
        }
        session.close();
    }
}
