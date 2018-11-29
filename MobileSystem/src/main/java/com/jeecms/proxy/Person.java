package com.jeecms.proxy;

public class Person implements Iperson {

    @Override
    public void doSomething() {
        System.out.println("I want to sell this house");
    }

    @Override
    public void getSomething() {
       
        System.out.println(" hello world "); 
    }

}
