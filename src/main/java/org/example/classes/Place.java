package org.example.classes;

public class Place extends Element {
    private String name;
    private String adress;
    private int capacity;


    public Place(String name, String adress, int capacity) {
        super();
        this.name = name;
        this.adress = adress;
        this.capacity = capacity;
    }


    public String getName() {
        return name;
    }
    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return String.format("Lieu numéro %d : %s, pouvant accueillir %d personnes. %s", this.id, this.name, this.capacity, this.adress);
    }
}
