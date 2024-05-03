package com.mysite.springboard;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {
    String hello;
    int lombok;


    public static void main(String[] args){
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setHello("hellog");

        System.out.println("helloLombok = " +helloLombok.getHello());

    }
}
