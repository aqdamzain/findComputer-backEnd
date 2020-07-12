package com.tempest.tech.demo.controllers;

import com.tempest.tech.demo.models.User;
import com.tempest.tech.demo.models.UserProfile;
import com.tempest.tech.demo.utils.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.tempest.tech.demo.services.DatabaseUser;
import com.tempest.tech.demo.utils.EmailAlreadyExistsException;

import java.util.ArrayList;

@CrossOrigin
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
    public User loginUser(@RequestParam(value="email") String email,
                          @RequestParam(value="password") String password)
    {
        try {
            return DatabaseUser.userLogin(email, password);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return null;
        }
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
