package com.tempest.tech.demo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptData {
    private final static String tokenMaster = "1Man@Black04Hide";
    public static String generate(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(str.getBytes());
            return bytesToHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateToken(String str) {
        MessageDigest md = null;
        String cacher = generate(tokenMaster);
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest((str+cacher).getBytes());
            return bytesToHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String bytesToHex(byte[] digest) {
        StringBuffer hexString = new StringBuffer();

        for (int i = 0;i<digest.length;i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        return hexString.toString();
    }
}
