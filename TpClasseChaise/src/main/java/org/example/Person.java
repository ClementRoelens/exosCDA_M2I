package org.example;

public class Person {
    protected int age;


    public void setAge(int age) {
        this.age = age;
    }

    public void sayHello(){
        System.out.println("Hello");
    }

    public void displayAge(){
        System.out.printf("J'ai %d ans\n",this.age);
    }
}
