package com.example.contactmanager.data.entity;

public class Contact {
    //constants
    public static final String TABLE_NAME ="contacts";
    public static final String COLUMN_ID ="contact_id";
    public static final String COLUMN_NAME ="contact_name";
    public static final String COLUMN_EMAIL ="contact_email";
    public static final String COLUMN_NUMBER="contact_number";

    //variables
    private String name;
    private String email;
    private String number;
    private int id;

    //constructor
    public Contact(){

    }

    public Contact(String name, String email, String number, int id) {
        this.name = name;
        this.email = email;
        this.number=number;
        this.id = id;
    }

    //getter setter


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //SQL query: creating table
    public static final String CREATE_TABLE=
            " CREATE TABLE "+ TABLE_NAME +"("+ COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COLUMN_NAME +" TEXT, "+
                    COLUMN_NUMBER+" TEXT, "+
                    COLUMN_EMAIL +" DATETIME DEFAULT CURRENT_TIMESTAMP"+")";
}
