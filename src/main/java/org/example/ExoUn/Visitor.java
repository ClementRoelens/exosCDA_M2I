package org.example.ExoUn;

import org.example.ExoUn.Entities.Animal;

public class Visitor implements Observer {
    private int id;
    private static int count;
    private String name;


     @Override
    public void getNotified(Animal animal) {
         System.out.printf("%s a été notifié qu'un nouvel animal était visible au zoo : le %s", this.name, animal.getName());
    }
}
