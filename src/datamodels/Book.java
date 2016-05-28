package datamodels;

/**
 * Created by sudo on 5/26/16.
 */
public class Book {
    private long book_id;
    private String title;
    private String isbn10;
    private String isbn13;
    private String edition;
    private String author1;
    private String author2;
    private String author3;
    private String author4;
    private String author5;
    private String author6;
    private String create_datetime;
    private String create_user;
    private String modify_datetime;
    private String modify_user;
    private long owner_id;


    public Book(){}

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getAuthor2() {
        return author2;
    }

    public void setAuthor2(String author2) {
        this.author2 = author2;
    }

    public String getAuthor1() {
        return author1;
    }

    public void setAuthor1(String author1) {
        this.author1 = author1;
    }

    public String getAuthor3() {
        return author3;
    }

    public void setAuthor3(String author3) {
        this.author3 = author3;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getModify_datetime() {
        return modify_datetime;
    }

    public void setModify_datetime(String modify_datetime) {
        this.modify_datetime = modify_datetime;
    }

    public String getModify_user() {
        return modify_user;
    }

    public void setModify_user(String modify_user) {
        this.modify_user = modify_user;
    }

    public String getAuthor4() {
        return author4;
    }

    public void setAuthor4(String author4) {
        this.author4 = author4;
    }

    public String getAuthor5() {
        return author5;
    }

    public void setAuthor5(String author5) {
        this.author5 = author5;
    }

    public String getAuthor6() {
        return author6;
    }

    public void setAuthor6(String author6) {
        this.author6 = author6;
    }

    public String getCreate_datetime() {
        return create_datetime;
    }

    public void setCreate_datetime(String create_datetime) {
        this.create_datetime = create_datetime;
    }
}