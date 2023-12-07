package org.example.ExoUn.Entities;

import org.example.ExoUn.Observer;

public class Mammal extends Animal {
    public Mammal(String name) {
        super(name);
    }

    @Override
    public String describe() {
        return this.name + " a des poils comme tous les mammifères";
    }
}
