package org.example.ExoUn.Factory;

import org.example.ExoUn.Entities.Animal;
import org.example.ExoUn.Entities.Bird;
import org.example.ExoUn.Observer;

public class BirdFactory extends AnimalFactory{
    @Override
    public Bird createAnimal(String name) {
        return new Bird(name);
    }
}
