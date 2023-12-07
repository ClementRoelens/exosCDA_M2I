package org.example.ExoUn.Factory;

import org.example.ExoUn.Entities.Animal;
import org.example.ExoUn.Entities.Mammal;

public class MammalFactory extends AnimalFactory {
    @Override
    public Mammal createAnimal(String name) {
        return new Mammal(name);
    }
}
