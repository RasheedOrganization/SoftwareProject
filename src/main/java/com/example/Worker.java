package com.example;

public class Worker {

    private String id,name,phone,availability;
    public Worker(String id, String name, String phone, String availability) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.availability = availability;
    }
    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvailability() {
        return this.availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }


}
