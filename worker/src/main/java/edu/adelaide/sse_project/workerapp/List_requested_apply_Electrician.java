package edu.adelaide.sse_project.workerapp;

public class List_requested_apply_Electrician {
    private String maid;
    private String gender;
    private String hours;
    private String time;
    private String date;
    private String city;
    private String address;

    public List_requested_apply_Electrician() {
    }

    public List_requested_apply_Electrician(String maid, String gender, String hours, String time, String date, String city, String address) {
        this.maid = maid;
        this.gender = gender;
        this.hours = hours;
        this.time = time;
        this.date = date;
        this.city = city;
        this.address = address;
    }

    public String getMaid() {
        return maid;
    }

    public String getGender() {
        return gender;
    }

    public String getHours() {
        return hours;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }
}
