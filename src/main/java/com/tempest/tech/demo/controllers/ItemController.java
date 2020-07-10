package com.tempest.tech.demo.controllers;

import com.tempest.tech.demo.models.Item;
import com.tempest.tech.demo.models.ItemLog;
import com.tempest.tech.demo.services.DatabaseItem;
import com.tempest.tech.demo.services.DatabaseUser;
import com.tempest.tech.demo.utils.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/item")
@RestController
public class ItemController {

    @RequestMapping("")
    public ArrayList<Item> getItemDatabase(){
        return DatabaseItem.getDB_ITEM();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Item insertItem(@RequestParam(value = "tokenId") String tokenId,
                                @RequestParam(value = "name") String name,
                                @RequestParam(value = "desc") String description,
                                @RequestParam(value = "category") String category,
                                @RequestParam(value = "price") double price,
                                @RequestParam(value = "itemImg") String itemImg)
    {
        try {
            return DatabaseItem.addItem(name, description, category, price, itemImg, DatabaseUser.getUser(tokenId));
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

    @RequestMapping("buy/user")
    public ArrayList<ItemLog> getBuyHistoryByUser(@RequestParam(value = "tokenId") String tokenId){
        return DatabaseItem.getBuyHistoryByUser(tokenId);
    }

    @RequestMapping("/search")
    public ArrayList<Item> getItemsByCategory(@RequestParam(value = "category") String category){
        return DatabaseItem.searchByCategory(category);
    }

}
