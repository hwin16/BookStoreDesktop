package user;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import datamodels.Book;

/**
 * Explore the whole library with search criteria
 * => can display books in the area
 * => can search for various books by name
 */
public class ExploreLibrary {
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    // ExploreLibrary constructor
    public ExploreLibrary() {}

    // list all the books in the database
    // parameter called searchArea will be added to limit the search
    public void listAllBooks() {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List query = session.createQuery("from Book b order by b.title, b.edition").list();
        tabularBooks(query);
        t.commit();
        session.close();
    }

    // list the books in tabular format
    // ## made a quick fix to make it public so that it can be used in managebooks
    // will create a new helper class to make this method modular and reusable
    public void tabularBooks(List<Book> books) {
        // very very ugly. sorry about that.
        System.out.printf("+--+------------------------------+----------+-------------+-------+--------------------+----" +
                "----------------+\n");
        System.out.printf("|%-2s|%-30s|%-10s|%-13s|%-7s|%-20s|%-20s|\n", "#", "Title", "ISBN10", "ISBN13", "Edition",
                          "Author1", "Author2");
        System.out.printf("+--+------------------------------+----------+-------------+-------+--------------------+----" +
                "----------------+\n");
        int i = 1;
        for (Book book : books) {
            System.out.printf("|%-2s|%-30s|%-10s|%-13s|%-7s|%-20s|%-20s|\n", i, book.getTitle(), book.getIsbn10(),
                    book.getIsbn13(),
                                book.getEdition(), book.getAuthor1(), book.getAuthor2());
            i++;
        }
        System.out.printf("+--+------------------------------+----------+-------------+-------+--------------------+---" +
                "-----------------+\n");
    }

    // search books by its name
    // TODO: make the parameter extendable list
    public void searchBooks(String query) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List result = session.createQuery("from Book where title like :query").setParameter("query", "%" +query + "%").list();
        tabularBooks(result);
        t.commit();
        session.close();
    }
}
