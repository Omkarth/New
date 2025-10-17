package edu.adelaide.sse_project.clientside;

public class tempmaiduser {
    final String gender;
    final String hours;
    final String city;
    final String time;
    final String address;
    final String permanent;


    public tempmaiduser(String gender, String hours, String city, String time, String address, String permanent) {
        this.gender = gender;
        this.hours = hours;
        this.city = city;
        this.time = time;
        this.address = address;
        this.permanent = permanent;
    }

    public String getGender() {
        return gender;
    }

    public String getHours() {
        return hours;
    }

    public String getCity() {
        return city;
    }

    public String getTime() {
        return time;
    }

    public String getAddress() {
        return address;
    }

    public String getPermanent() {
        return permanent;
    }
}
