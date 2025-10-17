package edu.adelaide.sse_project.clientside;

public class TempElectdatabase {

    final String gender;
    final String hours;
    final String time;
    final String date;
    final String city;
    final String address;
    final String maid;

    public TempElectdatabase(String gender, String hours, String time, String date, String city, String address, String maid) {
        this.gender = gender;
        this.hours = hours;
        this.time = time;
        this.date = date;
        this.city = city;
        this.address = address;
        this.maid = maid;
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

    public String getMaid() {
        return maid;
    }
}
