package com.tempest.tech.demo.utils;


import java.util.UUID;

public class RandomId {

    public static String generate(){
        return EncryptData.generate(UUID.randomUUID().toString());
    }

}
