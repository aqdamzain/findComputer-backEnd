package com.tempest.tech.demo.services;

import com.tempest.tech.demo.models.User;
import com.tempest.tech.demo.models.UserAuth;
import com.tempest.tech.demo.models.UserProfile;
import com.tempest.tech.demo.utils.EmailAlreadyExistsException;
import com.tempest.tech.demo.utils.EncryptData;
import com.tempest.tech.demo.utils.RandomId;
import com.tempest.tech.demo.utils.UserNotFoundException;

import java.util.ArrayList;

public class DatabaseUser {
    private static ArrayList<UserAuth> DB_USER_AUTH = new ArrayList<>();
    private static ArrayList<UserProfile> DB_USER = new ArrayList<>();

    public static ArrayList<UserProfile> getDB_USER(){
        return DB_USER;
    }

    public static User addUser(String name, String email, String password) throws EmailAlreadyExistsException {
        boolean duplicateEmail = false;
        if(!DB_USER_AUTH.isEmpty()){
            for(UserAuth fAuth: DB_USER_AUTH){
                if(fAuth.getEmail().equals(email)){
                    duplicateEmail = true;
                    break;
                }
            }
        }
        if(!duplicateEmail){
            //generate userId and check if there is already same userId;
            String userId;
            boolean duplicateId;
            do {
                duplicateId = false;
                userId = RandomId.generate();
                if(!DB_USER.isEmpty()){
                    for(UserProfile fUser: DB_USER){
                        if(fUser.getId().equals(userId)){
                            duplicateId = true;
                        }
                    }
                }
            } while(duplicateId);
            UserProfile user = new UserProfile(userId, name);
            DB_USER.add(user);
            UserAuth auth = new UserAuth(EncryptData.generateToken(userId), email, password);
            DB_USER_AUTH.add(auth);
            return new User(auth.getAuthId(), user);
        } else {
            throw new EmailAlreadyExistsException(email);
        }
    }

    public static User userLogin(String email, String password) throws UserNotFoundException {
        if(!DB_USER_AUTH.isEmpty()){
            for (UserAuth fAuth: DB_USER_AUTH){
                if(fAuth.getEmail().equals(email) && fAuth.getPassword().equals(password)){

                    return new User(fAuth.getAuthId(), getUser(fAuth.getAuthId()));
                }
            }
        }
        return null;
    }

    public static UserProfile getUser(String authId) throws UserNotFoundException {
        if(!DB_USER.isEmpty()){
            for (UserProfile fUser: DB_USER){
                if(authId.equals(EncryptData.generateToken(fUser.getId()))){
                    return fUser;
                }
            }
        }
        throw new UserNotFoundException(authId);
    }

    public static UserProfile changeProfileInfo(String authId, String name, String address, String profileImg){
        if(!DB_USER.isEmpty()){
            for (UserProfile fUser: DB_USER){
                if(authId.equals(EncryptData.generateToken(fUser.getId()))){
                    fUser.setName(name);
                    fUser.setAddress(address);
                    fUser.setProfileImg(profileImg);
                    return fUser;
                }
            }
        }
        return null;
    }

    public static String changePassword(String authId, String oldPwd, String newPwd){
        if(!DB_USER_AUTH.isEmpty()){
            for (UserAuth fAuth: DB_USER_AUTH){
                if(fAuth.getAuthId().equals(authId) && oldPwd.equals(fAuth.getPassword())){
                    fAuth.setPassword(newPwd);
                    return "success";
                }
            }
        }
        return "failed";
    }
}
