package org.keyin.gymproduct;

public class GymProduct {
    private int id;
    private String productName;
    private String productType;
    private double price;
    private int quantity;


    //constructor
    public GymProduct(String productName, String productType, double price, int quantity) {
        this.productName = productName;
        this.productType = productType;
        this.price = price;
        this.quantity = quantity;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return productType;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //toString
    @Override
    public String toString() {
        return "GymProduct" + "Product id:" + id + ", Product Name" + productName  + ", Product Type: " + productType + ", Price: " + price + ", Quantity in stock: " + quantity;
    }
}
