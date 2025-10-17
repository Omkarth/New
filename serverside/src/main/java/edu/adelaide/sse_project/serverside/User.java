package edu.adelaide.sse_project.serverside;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String gender;
    private String mobileno;
    private String address;
    private String pincode;
    private String workinghours;
    private String profiletype;

    public User() {
    }

    public User(String firstname, String lastname, String email, String password, String gender, String mobileno, String address, String pincode, String workinghours, String profiletype) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.mobileno = mobileno;
        this.address = address;
        this.pincode = pincode;
        this.workinghours = workinghours;
        this.profiletype = profiletype;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getWorkinghours() {
        return workinghours;
    }

    public void setWorkinghours(String workinghours) {
        this.workinghours = workinghours;
    }

    public String getProfiletype() {
        return profiletype;
    }

    public void setProfiletype(String profiletype) {
        this.profiletype = profiletype;
    }
}
