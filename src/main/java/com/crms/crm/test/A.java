package com.crms.crm.test;

public class A {
    int x = 10 ;

    public static void main(String[] args) {
     B b1 = new B();
     int x = b1.otpGen();
        System.out.println(x);
    }

    public int test() {
        return 100;
    }
}
