package user;

import datamodels.Book;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ManageBooks {
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public ManageBooks() {}

    // add books
    public void addBooks(String title, String edition, long owner_id) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Long book_id = null;
        Book book = new Book();
        book.setTitle(title);
        book.setEdition(edition);

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        book.setCreate_datetime(df.format(date));

        book.setModify_datetime(df.format(date));
        book.setOwner_id(owner_id);
        book_id = (Long)session.save(book);
        t.commit();
        session.close();
    }

    // update books
    public void updateBook(long book_id, String field, String value, long people_id){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery("update Book set " + field + " = :value where owner_id = :people_id and book_id = :book_id")
                    .setParameter("book_id", book_id)
                    .setParameter("value", value)
                    .setParameter("people_id", people_id);

        int num = query.executeUpdate();
        t.commit();
        session.close();
    }

    // delete books
    public void deleteBooks(Long book_id, long owner_id) {
        Session session = factory.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            Book book = (Book) session.get(Book.class, book_id);
            session.delete(book);
            t.commit();
        } catch (HibernateException e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // list all of my books
    public void listBooks(long owner_id) {
        Session session = factory.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            List books = session.createQuery("FROM Book where owner_id = :owner").setParameter("owner", owner_id).list();
            ExploreLibrary el = new ExploreLibrary();
            el.tabularBooks(books);
            t.commit();
        } catch (HibernateException e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
