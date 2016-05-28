import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import user.ExploreLibrary;
import user.ManageBooks;

import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.io.IOException; 
public class Menu {
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    // horizontal line
    private static void printlnHorizontal() {
        for (int i = 0; i < 100; i++){
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

    public static void updatemenu() {
        System.out.println("1. Change title");
        System.out.println("2. Change edition");
        System.out.println("3. Change author");
    }

    public static void exploremenu() {
        System.out.println("1. Show all books");
        System.out.println("2. Search for a book");
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

        printlnHorizontal();
        // Menu
        mainmenu(); 

        System.out.print("Please type the number: "); 
        int choices = Integer.parseInt(reader.readLine());
        printlnHorizontal();
        while (choices != 4) {
            if (choices == 2){
                ExploreLibrary el = new ExploreLibrary();
                el.listAllBooks();
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
                    System.out.print("Author: ");
                    author = reader.readLine();
                    mb.addBooks(title, edition, author, username);
                } else if (bchoice == 2) {
                    updatemenu();
                    System.out.print("Please type the number: ");
                    int ubook = Integer.parseInt(reader.readLine());
                    if (ubook == 1) {

                    }
                } else if (bchoice == 3) {
                    String title = reader.readLine();

                    Session session = factory.openSession();
                    Transaction t = null;
                    t = session.beginTransaction();
                    int book_id = (int) session.createQuery("select book_id from Book where owner = :username and title like :title")
                            .setString("username", username)
                            .setString("title", title).uniqueResult();
                    mb.deleteBooks(book_id);
                } else {
                    mb.listBooks(username);
                }
            }
            printlnHorizontal();
            mainmenu();

            System.out.print("Please type the number: ");
            choices = Integer.parseInt(reader.readLine());
            printlnHorizontal();
        }
        printlnHorizontal();
        // close the bufferedreader
        reader.close();
    }

}