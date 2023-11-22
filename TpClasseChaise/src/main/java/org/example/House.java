package org.example;

public class House {
    protected int surface;
    protected Door door;
    public House(int surface, Door door) {
        this.surface = surface;
        this.door = door;
    }
    public int getSurface() {
        return surface;
    }
    public void setSurface(int surface) {
        this.surface = surface;
    }
    public Door getDoor() {
        return door;
    }
    public void display(){
        System.out.printf("Je suis une maison, ma surface est de %dm²\n",this.surface);
    }
}
