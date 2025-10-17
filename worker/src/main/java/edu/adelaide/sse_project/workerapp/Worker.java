package edu.adelaide.sse_project.workerapp;

public class Worker {

    String firstname;
    String lastname;
    String email;
    String gender;
    String workinghours;
    String address;
    String pincode;
    String mobileno;
    String profiletype;

    public Worker() {
    }

    public Worker(String firstname, String lastname, String email, String gender, String workinghours, String address, String pincode, String mobileno, String profiletype) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.workinghours = workinghours;
        this.address = address;
        this.pincode = pincode;
        this.mobileno = mobileno;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWorkinghours() {
        return workinghours;
    }

    public void setWorkinghours(String workinghours) {
        this.workinghours = workinghours;
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

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getProfiletype() {
        return profiletype;
    }

    public void setProfiletype(String profiletype) {
        this.profiletype = profiletype;
    }
}


