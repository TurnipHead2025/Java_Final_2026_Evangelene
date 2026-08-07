package org.keyin.user.childclasses;

import org.keyin.user.User;

public class Admin extends User {

    //constuctor
    public Admin(String userName, String password, String email, String address, String phoneNumber, String role){
        super(userName, password, email, address, phoneNumber, "Admin");
    }
}
