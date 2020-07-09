package com.tempest.tech.demo.controllers;

import com.tempest.tech.demo.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tempest.tech.demo.services.DatabaseUser;
import com.tempest.tech.demo.utils.EmailAlreadyExistsException;

@RequestMapping("/user")
@RestController
public class UserController {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@RequestParam(value="name") String name,
                                 @RequestParam(value="email") String email,
                                 @RequestParam(value="password") String password)
    {
        try {
            DatabaseUser.addUser(name, email, password);
        } catch (EmailAlreadyExistsException e) {
            return e.getMessage();
        }
        return "success";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User loginUser(@RequestParam(value="email") String email,
                                  @RequestParam(value="password") String password)
    {
        return DatabaseUser.userLogin(email, password);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String userUpdate(@RequestParam(value="id") String id,
                                @RequestParam(value="name") String name,
                                @RequestParam(value="address") String address,
                                @RequestParam(value ="profileImg") String profileImg)
    {
        return DatabaseUser.changeProfileInfo(id, name, address, profileImg);
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    public String passwordUpdate(@RequestParam(value="id") String id,
                                    @RequestParam(value="pw") String password){
        return DatabaseUser.changePassword(id, password);
    }


}
