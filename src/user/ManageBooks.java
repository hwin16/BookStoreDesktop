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
            Book book = new Book(title, edition, author, username);
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
            book.setAuthor(author);
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

    // list books from all the database
    public void listBooks() {
        Session session = factory.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            List books = session.createQuery("FROM Book").list();
            for (Iterator i = books.iterator(); i.hasNext(); ) {
                Book book = (Book) i.next();
                System.out.print("Title: " + book.getTitle() + " ");
                System.out.print("Edition: " + book.getEdition() + " ");
                System.out.print("Author: " + book.getAuthor() + " ");
            }
            t.commit();
        } catch (HibernateException e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
