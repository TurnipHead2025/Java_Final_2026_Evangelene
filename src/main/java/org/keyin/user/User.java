package org.keyin.user;


public class User {
    // instance var or field 
    private int id;    
    private String userName;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    private String role;

    //Constructor
    public User(String userName, String password, String email, String address, String phoneNumber, String role){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    //Getters
    public int getId(){
        return id;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public String getEmail(){
        return email;
    }

    public String getAddress(){
        return address;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getRole(){
        return role;
    }

    //Setters
    public void setId(int id){
        this.id = id;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public void setRole(String role){
        this.role = role;
    }

   //toString
    @Override
public String toString() {
    return "User{id=" + id + ", userName='" + userName + "', email='" + email + "', address= '" + address +  "', phone number= '" + phoneNumber + "', role='" + role + "'}";
}

}


