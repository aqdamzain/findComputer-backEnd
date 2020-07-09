package com.tempest.tech.demo.services;

import com.tempest.tech.demo.models.User;
import com.tempest.tech.demo.utils.EmailAlreadyExistsException;
import com.tempest.tech.demo.utils.EncryptData;
import com.tempest.tech.demo.utils.RandomId;

import java.util.ArrayList;

public class DatabaseUser {
    public static ArrayList<User> DB_USER = new ArrayList<>();

    public static void addUser(String name, String email, String password) throws EmailAlreadyExistsException {
        boolean fEmail = false;
        if(!DB_USER.isEmpty()){
            for(User fUser: DB_USER){
                if(fUser.getEmail().equals(email)){
                    fEmail = true;
                }
            }
        }
        if(!fEmail){
            String userId;

            //generate userId and check if there is already same userId;
            boolean fId = false;
            do {
                userId = RandomId.generate();
                if(!DB_USER.isEmpty()){
                    for(User fUser: DB_USER){
                        if(fUser.getId().equals(userId)){
                            fId = true;
                        }
                    }
                }
            } while(fId);

            DB_USER.add(new User(userId, name, email, password));
        } else {
            throw new EmailAlreadyExistsException(email);
        }
    }

    public static User userLogin(String email, String password) {
        if(!DB_USER.isEmpty()){
            for (User fUser: DB_USER){
                if(fUser.getEmail().equals(email) && fUser.getPassword().equals(password)){
                    return fUser;
                }
            }
        }
        return null;
    }

    private static User getUser(String id) {
        if(!DB_USER.isEmpty()){
            for (User fUser: DB_USER){
                if(fUser.getId().equals(id)){
                    return fUser;
                }
            }
        }
        return null;
    }

    public static String changeProfileInfo(String id, String name, String address, String profileImg){
        if(!DB_USER.isEmpty()){
            for (User fUser: DB_USER){
                if(fUser.getId().equals(id)){
                    fUser.setName(name);
                    fUser.setAddress(address);
                    fUser.setProfileImg(profileImg);
                    return "success";
                }
            }
        }
        return "userNotFound";
    }

    public static String changePassword(String id, String password){
        if(!DB_USER.isEmpty()){
            for (User fUser: DB_USER){
                if(fUser.getId().equals(id)){
                    fUser.setPassword(password);
                    return "success";
                }
            }
        }
        return "failed";
    }
}
