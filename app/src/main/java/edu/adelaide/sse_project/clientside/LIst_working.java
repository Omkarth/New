package edu.adelaide.sse_project.clientside;


public class LIst_working {
    private String firstname;
    private String gender;
    private String mobileno;
    private String workinghours;
    private String profiletype;
    private String Status;

    public LIst_working() {
    }

    public LIst_working(String firstname, String gender, String mobileno, String workinghours, String profiletype, String status) {
        this.firstname = firstname;
        this.gender = gender;
        this.mobileno = mobileno;
        this.workinghours = workinghours;
        this.profiletype = profiletype;
        Status = status;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}