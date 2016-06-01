import datamodels.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import user.ExploreLibrary;
import user.ManageBooks;
import user.UpdateProfile;

import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.io.IOException;

public class Menu {
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    // horizontal line
    private static void printlnHorizontal() {
        for (int i = 0; i < 130; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    public static void mainmenu() {
        System.out.println("1. Update Profile");
        System.out.println("2. Explore book database");
        System.out.println("3. Add/update your books"); 
        System.out.println("4. Log out");
    }

    public static void bookmenu() {
        System.out.println("1. Add a new book");
        System.out.println("2. Update an existing book");
        System.out.println("3. Delete an existing book");
        System.out.println("4. Show my bookstore");
    }

    public static void exploremenu() {
        System.out.println("1. Show all books");
        System.out.println("2. Search for a book");
    }

    private static long getId(String username, String password) {
        Session session = factory.openSession();
        long id = (long)session.createQuery("Select people_id from User where username = :username and password = :password")
                  .setParameter("username", username)
                  .setParameter("password", password).uniqueResult();
        session.close();
        return id;
    }

    public static void main(String[] args) throws IOException{
        System.out.println("Welcome to BookStore!!!");

        // User input 
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String username, password; 

        // username 
        System.out.print("username: ");
        username = reader.readLine(); 

        // password
        System.out.print("password: ");
        password = reader.readLine();  
        System.out.println(); 

        // username password authentication
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        User user = (User) session.createQuery("FROM User WHERE username = :username and password = :password")
                    .setParameter("username", username)
                    .setParameter("password", password).uniqueResult();
        if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
            System.out.println("Authentication successful.");
        }
        else {
            System.out.println("Incorrect username or password");
            System.exit(-1);
        }
        t.commit();

        printlnHorizontal();

        // main menu set up
        mainmenu();
        System.out.print("Please type the number: ");
        int choices = 0;
        try {
            choices = Integer.parseInt(reader.readLine());
        }
        catch (NumberFormatException e){
            System.out.println("Not a valid option. Quitting bookstore!");
            System.exit(0);
        }
        printlnHorizontal();

        // userID
        long id = getId(username, password);
        // menu options
        while (choices != 4) {
            if (choices == 1){
                UpdateProfile up = new UpdateProfile();
                up.showCurrentProfile(id);
                System.out.print("Please type the field you want to update(firstname, birthdate, etc): ");
                String field = reader.readLine();
                System.out.print("Please type in the updated value(birthdate=> YYYY-MM-DD): ");
                String value = reader.readLine();
                up.updateData(field, value, getId(username, password));
                up.showCurrentProfile(id);
            }
            else if (choices == 2){
                ExploreLibrary el = new ExploreLibrary();
                el.listAllBooks();
                System.out.print("Search for a book by Title: " );
                String title = reader.readLine();
                el.searchBooks(title);
            }
            else if (choices == 3) {
                bookmenu();
                ManageBooks mb = new ManageBooks();
                System.out.print("Please type the number: ");
                int bchoice = Integer.parseInt(reader.readLine());
                if (bchoice == 1) {
                    String title, edition, author;
                    System.out.print("Title: ");
                    title = reader.readLine();
                    System.out.print("Edition: ");
                    edition = reader.readLine();
                    mb.addBooks(title, edition, id);
                } else if (bchoice == 2) {
                    mb.listBooks(id);

                    // menu options
                    System.out.print("Please type the title of the book you want to update: ");
                    String title = reader.readLine();
                    System.out.print("Please type the field you want to update(title, edition, etc): ");
                    String field = reader.readLine();
                    System.out.print("Please type in the updated value: ");
                    String value = reader.readLine();

                    // get book_id
                    long book_id = (long) session.createQuery("select book_id from Book where title = :title and owner_id = :id")
                                    .setParameter("title", title)
                                    .setParameter("id", id).uniqueResult();
                    mb.updateBook(book_id, field, value, id);
                    mb.listBooks(id);
                } else if (bchoice == 3) {
                    mb.listBooks(id);
                    System.out.print("Please type the title: ");
                    String title = reader.readLine();
                    Transaction trans = session.beginTransaction();
                    long book_id = (long) session.createQuery("select book_id from Book where owner_id = :username and title like :title")
                            .setParameter("username", getId(username, password))
                            .setParameter("title", "%" + title + "%").uniqueResult();

                    mb.deleteBooks(book_id, id);
                    trans.commit();
                } else {
                    mb.listBooks(id);
                }
            }
            printlnHorizontal();
            mainmenu();

            System.out.print("Please type the number: ");
            choices = Integer.parseInt(reader.readLine());
            printlnHorizontal();
        }
        session.close();
        printlnHorizontal();
        reader.close();
        System.out.println("Thank you for using bookstore!");
    }

}