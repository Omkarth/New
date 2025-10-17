package edu.adelaide.sse_project.serverside;

public class List_requested_Chef {
    String chef;
    String gender;
    String hours;
    String time;
    String date;
    String city;
    String address;

    public List_requested_Chef() {
    }

    public List_requested_Chef(String chef, String gender, String hours, String time, String date, String city, String address) {
        this.chef = chef;
        this.gender = gender;
        this.hours = hours;
        this.time = time;
        this.date = date;
        this.city = city;
        this.address = address;
    }

    public String getChef() {
        return chef;
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
