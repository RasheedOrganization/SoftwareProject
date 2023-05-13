package com.example;

public class Product {
    private String ID;
    private double price;
    private String name;
    private String area;
    private String quantity;
    private String address;
    private String status;
    private String date;

    public Product(String id, double price, String name, String area, String quantity, String address, String status, String date) {
        this.ID = id;
        this.price = Math.round(price * 1000) / 1000.0;
        this.name = name;
        this.area = area;
        this.quantity = quantity;
        this.address = address;
        this.status = status;
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
