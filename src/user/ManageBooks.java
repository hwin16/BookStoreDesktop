package user;

import datamodels.Book;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class ManageBooks {
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public ManageBooks() {}

    // add books
    public Integer addBooks(String title, String edition, String author, String username) {
        Session session = factory.openSession();
        Transaction t = null;
        Integer book_id = null;
        try {
            t = session.beginTransaction();
            Book book = new Book();
            book_id = (Integer) session.save(book);
            t.commit();
        } catch (HibernateException e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return book_id;
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
    public void deleteBooks(Integer book_id) {
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

    private void tabularlistBooks(List books){
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
    public void listBooks(String username) {
        Session session = factory.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            List books = session.createQuery("FROM Book where owner = :owner").setParameter("owner", username).list();
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
