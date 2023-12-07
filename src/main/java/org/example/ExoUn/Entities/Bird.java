package org.example.ExoUn.Entities;

import org.example.ExoUn.Observer;

import java.util.List;

public class Bird extends Animal {

    public Bird(String name) {
        super(name);
    }

    @Override
    public String describe() {
        return this.name + " est enfermé dans une volière comme tous les oiseaux";
    }



}
