package user;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

import datamodels.Book;

public class ExploreLibrary {
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public ExploreLibrary() {}

    public void listAllBooks() {
        Session session = factory.openSession();
        Transaction t = null;
        t = session.beginTransaction();
        List query = session.createQuery("from Book b order by b.title, b.edition").list();
        bookIterator(query);
        t.commit();
        session.close();
    }

    private void bookIterator(List<Book> books) {
        for (Book book : books) {
            System.out.print(book.getTitle() + " ");
            System.out.print(book.getEdition() + " ");
            System.out.print(book.getAuthor1());
            System.out.println();
        }
    }

    public void searchBooks(String query) {
        Session session = factory.openSession();
        Transaction t = null;
        t = session.beginTransaction();
        List result = session.createQuery("from Book b where title like :query").setParameter("query", query).list();
        bookIterator(result);
        t.commit();
        session.close();
    }
}
