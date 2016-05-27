package datamodels;

/**
 * Created by sudo on 5/26/16.
 */

public class People {
    private int people_id;
    private String firstname;
    private String lastname;

    public People() {}
    public People(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getPeople_id() {
        return people_id;
    }

    public void setPeople_id(int people_id){
        this.people_id = people_id;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
         this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }
}
