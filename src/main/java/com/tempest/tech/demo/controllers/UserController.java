package com.tempest.tech.demo.controllers;

import com.tempest.tech.demo.models.User;
import com.tempest.tech.demo.models.UserProfile;
import com.tempest.tech.demo.storage.StorageService;
import com.tempest.tech.demo.utils.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tempest.tech.demo.services.DatabaseUser;
import com.tempest.tech.demo.utils.EmailAlreadyExistsException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;

@CrossOrigin
@RequestMapping("/user")
@RestController
public class UserController {

    private final StorageService storageService;

    @Autowired
    public UserController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping("")
    public ArrayList<UserProfile> getAllUsers(){
        return DatabaseUser.getDB_USER();
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,
                "image/jpeg").body(file);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User registerUser(@RequestParam(value="name") String name,
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
                                  @RequestParam(value ="file") MultipartFile profileImg)
    {
        storageService.store(profileImg);
        return DatabaseUser.changeProfileInfo(tokenId, name, address,
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
                        +"/user/files/"+ profileImg.getOriginalFilename());
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    public String passwordUpdate(@RequestParam(value="tokenId") String tokenId,
                                    @RequestParam(value="oldPw") String oldPwd,
                                    @RequestParam(value="newPw") String newPwd)
    {
        return DatabaseUser.changePassword(tokenId, oldPwd, newPwd);
    }


}
