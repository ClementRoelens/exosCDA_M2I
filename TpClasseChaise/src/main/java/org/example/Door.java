package org.example;

public class Door {
    private String color;
    public void display(){
        System.out.printf("Je suis une porte, ma couleur est %s\n",this.color);
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public Door(String color) {
        this.color = color;
    }
}
