package datamodels;

/**
 * Created by sudo on 5/27/16.
 */
public class User {
    private long people_id;
    private String username;
    private String password;

    public User() {}

    public long getPeople_id() {
        return people_id;
    }

    public void setPeople_id(long people_id) {
        this.people_id = people_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
