package com.example;

public class Invoice {
private String productName;
private double area;
private double quantity;
private double price;

    public Invoice(String productName, double area, double quantity, double price) {
        this.productName = productName;
        this.area = Math.round(area * 1000) / 1000.0;
        this.quantity = Math.round(quantity * 1000) / 1000.0;
        this.price = Math.round(price * 1000) / 1000.0;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productname) {
        this.productName = productname;
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
