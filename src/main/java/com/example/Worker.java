package com.example;

public class Worker {

    private String ID,name,phone,Availability;
    public Worker(String ID, String name, String phone, String Availability) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.Availability = Availability;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
        return this.Availability;
    }

    public void setAvailability(String Availability) {
        this.Availability = Availability;
    }


}
