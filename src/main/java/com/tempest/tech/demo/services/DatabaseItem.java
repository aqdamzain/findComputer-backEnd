package com.tempest.tech.demo.services;

import com.tempest.tech.demo.models.Item;
import com.tempest.tech.demo.models.ItemLog;
import com.tempest.tech.demo.models.UserProfile;
import com.tempest.tech.demo.utils.EncryptData;
import com.tempest.tech.demo.utils.RandomId;
import com.tempest.tech.demo.utils.UserNotFoundException;

import java.util.ArrayList;

public class DatabaseItem {

    private static ArrayList<Item> DB_ITEM = new ArrayList<>();
    private static ArrayList<ItemLog> DB_ITEM_HISTORY = new ArrayList<>();

    public static ArrayList<Item> getDB_ITEM(){
        return DB_ITEM;
    }

    public static Item addItem(String name, String description, String category, double price, String itemImg, UserProfile owner) {
        //generate userId and check if there is already same userId;
        String itemId;
        boolean duplicateId;
        do {
            duplicateId = false;
            itemId = RandomId.generate();
            if (!DB_ITEM.isEmpty()) {
                for (Item fItem : DB_ITEM) {
                    if (fItem.getId().equals(itemId)) {
                        duplicateId = true;
                    }
                }
            }
        } while (duplicateId);
        Item item = new Item(itemId, name, description, category, price, itemImg, owner);
        DB_ITEM.add(item);
        return item;
    }

    public static ArrayList<Item> getItemByUser(String authId) {
        ArrayList<Item> myItems = new ArrayList<>();
        if(!DB_ITEM.isEmpty()){
            for(Item fItem : DB_ITEM){
                if(authId.equals(EncryptData.generateToken(fItem.getOwner().getId()))){
                    myItems.add(fItem);
                }
            }
        }
        return myItems;
    }

    public static Item getItemById(String itemId){
        if(!DB_ITEM.isEmpty()){
            for(Item fItem : DB_ITEM){
                if(itemId.equals(fItem.getId())){
                    return fItem;
                }
            }
        }
        return null;
    }

    public static Item removeItem(String authId, String itemId){
        boolean removeState = false;
        Item item = null;
        if(!DB_ITEM.isEmpty()) {
            for (Item fItem : DB_ITEM) {
                if (fItem.getId().equals(itemId)) {
                    if(authId.equals(EncryptData.generateToken(fItem.getOwner().getId()))){
                        removeState = true;
                        item = fItem;
                    }
                    break;
                }
            }
            if(removeState){
                DB_ITEM.remove(item);
            }
        }
        return item;
    }

    public static ItemLog buyItem(String authId, String itemId) throws UserNotFoundException {
        boolean ownerItem = false;
        Item item = null;
        if(!DB_ITEM.isEmpty()) {
            for (Item fItem : DB_ITEM) {
                if (fItem.getId().equals(itemId)) {
                    if(authId.equals(EncryptData.generateToken(fItem.getOwner().getId()))){
                        ownerItem = true;
                    }
                    item = fItem;
                    break;
                }
            }
            if(!ownerItem){
                ItemLog itemLog = new ItemLog(item.getId(), item.getName(), item.getDescription(), item.getCategory(),
                        item.getPrice(), item.getItemImg(), item.getOwner(), DatabaseUser.getUser(authId));

                DB_ITEM_HISTORY.add(itemLog);
                DB_ITEM.remove(item);
                return itemLog;
            }
        }
        return null;
    }

    public static ArrayList<ItemLog> getBuyHistoryByUser(String authId) {
        ArrayList<ItemLog> myBuyHistory = new ArrayList<>();
        if(!DB_ITEM_HISTORY.isEmpty()){
            for(ItemLog fItem : DB_ITEM_HISTORY){
                if(authId.equals(EncryptData.generateToken(fItem.getBuyer().getId()))){
                    myBuyHistory.add(fItem);
                }
            }
        }
        return myBuyHistory;
    }

    public static ArrayList<Item> searchByCategory(String category){
        ArrayList<Item> searchRes = new ArrayList<>();
        if(!DB_ITEM.isEmpty()){
            for(Item fItem : DB_ITEM){
                if(category.equals(fItem.getCategory())){
                    searchRes.add(fItem);
                }
            }
            return searchRes;
        }
        return null;
    }
}
