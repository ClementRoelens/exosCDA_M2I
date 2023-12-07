package org.example.ExoUn.Factory;

import org.example.ExoUn.Entities.Animal;
import org.example.ExoUn.Observer;
import org.example.ExoUn.Subject;

public abstract class AnimalFactory{
    public abstract Animal createAnimal(String name);
}
