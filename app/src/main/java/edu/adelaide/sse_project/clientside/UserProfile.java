package edu.adelaide.sse_project.clientside;

public class UserProfile {
    public String userName;
    public String usreLastname;
    public String userEmail;
    public String userPassword;
    public String userMobileno;
    public String userAddress;
    public String userPincode;

    public  UserProfile(){

    }


    public UserProfile(String userName, String usreLastname, String userEmail, String userPassword, String userMobileno, String userAddress, String userPincode) {
        this.userName = userName;
        this.usreLastname = usreLastname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userMobileno = userMobileno;
        this.userAddress = userAddress;
        this.userPincode = userPincode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUsreLastname() {
        return usreLastname;
    }

    public void setUsreLastname(String usreLastname) {
        this.usreLastname = usreLastname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserMobileno() {
        return userMobileno;
    }

    public void setUserMobileno(String userMobileno) {
        this.userMobileno = userMobileno;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPincode() {
        return userPincode;
    }

    public void setUserPincode(String userPincode) {
        this.userPincode = userPincode;
    }
}