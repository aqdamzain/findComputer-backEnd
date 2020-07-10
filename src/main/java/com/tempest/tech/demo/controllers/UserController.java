package com.tempest.tech.demo.controllers;

import com.tempest.tech.demo.models.UserProfile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tempest.tech.demo.services.DatabaseUser;
import com.tempest.tech.demo.utils.EmailAlreadyExistsException;

import java.util.ArrayList;

@RequestMapping("/user")
@RestController
public class UserController {

    @RequestMapping("")
    public ArrayList<UserProfile> getAllUsers(){
        return DatabaseUser.getDB_USER();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserProfile registerUser(@RequestParam(value="name") String name,
                                 @RequestParam(value="email") String email,
                                 @RequestParam(value="password") String password)
    {
        try {
            return DatabaseUser.addUser(name, email, password);
        } catch (EmailAlreadyExistsException e) {
            return null;
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(@RequestParam(value="email") String email,
                                  @RequestParam(value="password") String password)
    {
        return DatabaseUser.userLogin(email, password);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public UserProfile userUpdate(@RequestParam(value="tokenId") String tokenId,
                                  @RequestParam(value="name") String name,
                                  @RequestParam(value="address") String address,
                                  @RequestParam(value ="profileImg") String profileImg)
    {
        return DatabaseUser.changeProfileInfo(tokenId, name, address, profileImg);
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    public String passwordUpdate(@RequestParam(value="tokenId") String tokenId,
                                    @RequestParam(value="oldPw") String oldPwd,
                                    @RequestParam(value="newPw") String newPwd)
    {
        return DatabaseUser.changePassword(tokenId, oldPwd, newPwd);
    }


}
