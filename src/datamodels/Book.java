package datamodels;

/**
 * Created by sudo on 5/26/16.
 */
public class Book {
    private int book_id;
    private String title;
    private String edition;
    private String author;
    private String owner;

    public Book(){}
    public Book(String title, String edition, String author, String owner){
        this.title = title;
        this.edition = edition;
        this.author = author;
        this.owner = owner;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}