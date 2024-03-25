package com.SindhiManu.Tipno;

import java.util.ArrayList;
import java.util.Collections;

public class
Pandit {

    private String name;
    private String address;
    private String city;
    private String state;
    private String pin;

    private ArrayList<String> phone_nos;
    private ArrayList<String> email_ids;

    public Pandit(){}

    public Pandit(String name, String address, String state, String city,String pin, ArrayList<String> phone_nos, ArrayList<String> email_ids) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pin = pin;
        this.phone_nos = phone_nos;
        for(String ph : phone_nos){
            System.out.println(ph);
            System.out.println();
        }
        this.email_ids = email_ids;
    }

    public Pandit(String name, String address, ArrayList<String> phone_nos, ArrayList<String> email_ids){
        this(name, address, "null", "null","null", phone_nos, email_ids);
    }

    public Pandit(String name, String address){
        this(name, address,null,null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public ArrayList<String> getPhone_nos() {
        return phone_nos;
    }

    public void setPhone_nos(ArrayList<String> phone_nos) {
        this.phone_nos = phone_nos;
    }

    public ArrayList<String> getEmail_ids() {
        return email_ids;
    }

    public void setEmail_ids(ArrayList<String> email_ids) {
        this.email_ids = email_ids;
    }
}
