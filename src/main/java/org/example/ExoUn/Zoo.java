package org.example.ExoUn;

import org.example.ExoUn.Entities.Animal;

import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private static Zoo instance = null;
    private static final Object lock = new Object();

    private List<Animal> animals;


    private Zoo() {
        this.animals = new ArrayList<>();
    }

    public Zoo getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new Zoo();
            }
        }
        return instance;
    }
}
