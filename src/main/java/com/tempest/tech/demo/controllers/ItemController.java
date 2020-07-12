package com.tempest.tech.demo.controllers;

import com.tempest.tech.demo.models.Item;
import com.tempest.tech.demo.models.ItemLog;
import com.tempest.tech.demo.services.DatabaseItem;
import com.tempest.tech.demo.services.DatabaseUser;
import com.tempest.tech.demo.storage.StorageService;
import com.tempest.tech.demo.utils.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.InetAddress;
import java.util.ArrayList;

@CrossOrigin
@RequestMapping("/item")
@RestController
public class ItemController {

    private final StorageService storageService;

    @Autowired
    public ItemController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping("")
    public ArrayList<Item> getItemDatabase(){
        return DatabaseItem.getDB_ITEM();
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,
                "image/jpeg").body(file);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Item insertItem(@RequestParam(value = "tokenId") String tokenId,
                                @RequestParam(value = "name") String name,
                                @RequestParam(value = "desc") String description,
                                @RequestParam(value = "category") String category,
                                @RequestParam(value = "price") double price,
                                @RequestParam(value = "file") MultipartFile imgFile)
    {
        storageService.store(imgFile);
        try {
            return DatabaseItem.addItem(name, description, category, price,
                    ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
                            +"/item/files/"+ imgFile.getOriginalFilename(),
                    DatabaseUser.getUser(tokenId));
        } catch (UserNotFoundException e) {
            return null;
        }
    }

    @RequestMapping("/user")
    public ArrayList<Item> getItemByUser(@RequestParam(value = "tokenId") String tokenId){
        return DatabaseItem.getItemByUser(tokenId);
    }

    @RequestMapping("/{id}")
    public Item getItem(@PathVariable String id){
        return DatabaseItem.getItemById(id);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public Item deleteItem(@RequestParam(value = "tokenId") String tokenId,
                           @PathVariable String id)
    {
        return DatabaseItem.removeItem(tokenId, id);
    }

    @RequestMapping(value = "/{id}/buy", method = RequestMethod.POST)
    public ItemLog buyItem(@RequestParam(value = "tokenId") String tokenId,
                           @PathVariable String id)
    {
        try {
            return DatabaseItem.buyItem(tokenId, id);
        } catch (UserNotFoundException e) {
            return null;
        }
    }

    @RequestMapping(value = "buy/user", method = RequestMethod.POST)
    public ArrayList<ItemLog> getBuyHistoryByUser(@RequestParam(value = "tokenId") String tokenId){
        return DatabaseItem.getBuyHistoryByUser(tokenId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ArrayList<Item> getItemsByCategory(@RequestParam(value = "category") String category){
        return DatabaseItem.searchByCategory(category);
    }

}
