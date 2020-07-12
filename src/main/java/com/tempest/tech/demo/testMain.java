package com.tempest.tech.demo;

import com.tempest.tech.demo.models.User;
import com.tempest.tech.demo.models.UserProfile;
import com.tempest.tech.demo.services.DatabaseUser;
import com.tempest.tech.demo.utils.EmailAlreadyExistsException;
import com.tempest.tech.demo.utils.RandomId;
import com.tempest.tech.demo.utils.UserNotFoundException;

import java.util.ArrayList;

public class testMain {
    public static void main(String[] args) {
        try {
            UserProfile newUser = DatabaseUser.addUser("Aqdam Zain", "aqdamzh@gmail.com", "1234");
            System.out.println(newUser.getId());
        } catch (EmailAlreadyExistsException e) {
            e.printStackTrace();
        }

        try {
            User loginResponse = DatabaseUser.userLogin("aqdamzh@gmail.com", "1234");
            System.out.println(loginResponse.getTokenId());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] digest) {
        StringBuffer hexString = new StringBuffer();

        for (int i = 0;i<digest.length;i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        return hexString.toString();
    }
}
