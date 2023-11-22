package org.example;

public class HousePerson {
    private String name;
    private House house;
    public void display(){
        System.out.printf("Je m'appelle %s\n", this.name);
        this.house.display();
        this.house.door.display();
    }

    public HousePerson(String name, House house) {
        this.name = name;
        this.house = house;
    }
}
