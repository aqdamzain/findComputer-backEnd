package com.tempest.tech.demo;

import com.tempest.tech.demo.utils.RandomId;

public class testMain {
    public static void main(String[] args) {
        System.out.println(RandomId.generate());

    }

    private static String bytesToHex(byte[] digest) {
        StringBuffer hexString = new StringBuffer();

        for (int i = 0;i<digest.length;i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        return hexString.toString();
    }
}
