package org.keyin.memberships;


public class Membership {
    private int id;
    private String membershipType;
    private double price;
    private int memberId;
    private String purchaseDate;

    //constructor
    public Membership(String membershipType, double price, int memberId, String purchaseDate){
        this.membershipType = membershipType;
        this.price = price;
        this. memberId = memberId;
        this.purchaseDate = purchaseDate;
    }

    //getters
    public int getId(){
        return id;
    }

    public String getMembershipType(){
        return membershipType;
    }

    public double getPrice(){
        return price;
    }

    public int getMemberId(){
        return memberId;
    }

    public String getPurchaseDate(){
        return purchaseDate;
    }

    
    //setters
    public void setId(int id){
        this.id = id;
    }

    public void setMembershipType(String membershipType){
        this.membershipType = membershipType;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setMemberId(int memberId){
        this. memberId = memberId;
    }

    public void setPurchaseDate(String purchaseDate){
        this.purchaseDate = purchaseDate;
    }

    //toString
    @Override
    public String toString(){
        return "Member Id: " + memberId + ", Membership Type: " + membershipType + ", Price: " + price + ", Purchase Date: " + purchaseDate;
    }
}
