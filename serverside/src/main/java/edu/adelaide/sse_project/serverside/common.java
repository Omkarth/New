package edu.adelaide.sse_project.serverside;

public class common {
    public static final String Update = "Update";
    public static final String Delete = "Delete";

    public static final int PICK_IMAGE_REQUEST = 71;


    public static String convertCodeToStatus(String code)
    {
        if(code.equals("0"))
            return "Placed";
        else if(code.equals("1"))
            return "On my way";
        else
            return "Shipped";
    }
}
