package com.tempest.tech.demo;

import com.tempest.tech.demo.utils.RandomId;

import java.util.ArrayList;

public class testMain {
    public static void main(String[] args) {
        class Test{
            final int a;
            Test(int a){
                this.a = a;
            }
            int getA(){
                return this.a;
            }
        }
        ArrayList<Test> testArr = new ArrayList<>();
        testArr.add(new Test(2));testArr.add(new Test(2));testArr.add(new Test(23));
        testArr.add(new Test(2));testArr.add(new Test(2));

        boolean testVar = false;
        for (Test test : testArr) {
            System.out.println(test.getA());
            if(test.getA()==23){
                testVar = true;
                break;
            }
        }
        System.out.println(testVar);
    }

    private static String bytesToHex(byte[] digest) {
        StringBuffer hexString = new StringBuffer();

        for (int i = 0;i<digest.length;i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        return hexString.toString();
    }
}
