package datamodels;

/**
 * Created by sudo on 5/26/16.
 */

public class People {
    private long people_id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String birthdate;
    private String addressline1;
    private String addressline2;
    private String addressline3;
    private String city;
    private String zip;
    private String state;
    private String create_datetime;
    private String create_user;
    private String modify_datetime;
    private String modify_user;

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getAddressline3() {
        return addressline3;
    }

    public void setAddressline3(String addressline3) {
        this.addressline3 = addressline3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreate_datetime() {
        return create_datetime;
    }

    public void setCreate_datetime(String create_datetime) {
        this.create_datetime = create_datetime;
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

    public People() {}

    public long getPeople_id() {
        return people_id;
    }

    public void setPeople_id(long people_id){
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
