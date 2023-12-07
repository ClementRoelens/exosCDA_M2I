package org.example.ExoUn.Entities;

import org.example.ExoUn.Observer;

public class Reptile extends Animal {
    public Reptile(String name) {
        super(name);
    }

    @Override
    public String describe() {
        return this.name + " a besoin d'une température régulée comme tous les reptiles";
    }
}
