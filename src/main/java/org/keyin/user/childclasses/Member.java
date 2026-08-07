package org.keyin.user.childclasses;

import org.keyin.user.User;

public class Member extends User {

    //constructor
    //constuctor
    public Member(String userName, String password, String email, String address, String phoneNumber, String role){
        super(userName, password, email, address, phoneNumber, "Member");
    }
}
