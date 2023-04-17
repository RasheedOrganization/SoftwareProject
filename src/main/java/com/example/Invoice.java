package com.example;

public class Invoice {
private String ProductName;
private double area,quantity,price;

    public Invoice(String productName, double area, double quantity, double price) {
        ProductName = productName;
        this.area = area;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
