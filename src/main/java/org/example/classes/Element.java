package org.example.classes;

public abstract class Element {
    protected int id;
    private static int count;


    public Element(){
        this.id = count++;
    }


    public int getId() {
        return id;
    }
}
