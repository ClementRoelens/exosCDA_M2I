package org.example.ExoUn.Factory;

import org.example.ExoUn.Entities.Animal;
import org.example.ExoUn.Entities.Reptile;

public class ReptileFactory extends AnimalFactory{
    @Override
    public Animal createAnimal(String name) {
        return new Reptile(name);
    }
}
