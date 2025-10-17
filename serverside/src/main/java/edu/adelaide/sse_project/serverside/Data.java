package edu.adelaide.sse_project.serverside;

public class Data {

    String id;
    String data;


    public void Data(){

    }

    public Data(String id, String data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }
}
