package edu.adelaide.sse_project.workerapp;

public class UserProfile {

    String firstname;
    String lastname;
    String gender;
    String workinghours;
    String mobileno;
    String address;
    String pincode;
    String profiletype;

    public UserProfile() {
    }

    public UserProfile(String firstname, String lastname, String gender, String workinghours, String mobileno, String address, String pincode, String profiletype) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.workinghours = workinghours;
        this.mobileno = mobileno;
        this.address = address;
        this.pincode = pincode;
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

    public String getProfiletype() {
        return profiletype;
    }

    public void setProfiletype(String profiletype) {
        this.profiletype = profiletype;
    }
}
