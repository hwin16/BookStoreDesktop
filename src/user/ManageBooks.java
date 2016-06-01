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
    // I want to change book name, price, edition
    public void updateBooks(Integer book_id, String title, String edition, String author){
        Session session = factory.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            Book book = (Book) session.get(Book.class, book_id);
            book.setTitle(title);
            book.setAuthor1(author);
            book.setTitle(title);
            book.setEdition(edition);
            session.update(book);
            t.commit();
        } catch (HibernateException e){
            if (t != null) t.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // delete books
    public void deleteBooks(Long book_id, long owner_id) {
        Session session = factory.openSession();
        Transaction t = null;
        ManageBooks mb = new ManageBooks();
        mb.listBooks(owner_id);
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

    private void tabularlistBooks(List<Book> books){
        System.out.format("+%40s+%20s+%20s+\n", "-", "-", "-");
        System.out.format("|%40s|%20s|%20s|\n", "Title", "Edition", "Author");
        System.out.format("+%40s+%20s+%20s+\n", "-", "-", "-");
        for (Iterator i = books.iterator(); i.hasNext();){
            Book book = (Book) i.next();
            System.out.format("|%40s|%20s|%20s|\n", book.getTitle(), book.getEdition(), book.getAuthor1());
        }
        System.out.format("+%40s+%20s+%20s+", "-", "-", "-");
    }

    // list all of my books
    public void listBooks(long owner_id) {
        Session session = factory.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            List books = session.createQuery("FROM Book where owner_id = :owner").setParameter("owner", owner_id).list();
            tabularlistBooks(books);
            t.commit();
        } catch (HibernateException e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
