package org.example.ExoUn.Entities;

import org.example.ExoUn.Observer;
import org.example.ExoUn.Subject;

import java.util.List;

public abstract class Animal implements Subject {
    protected int id;
    protected static int count;
    protected String name;
    private List<Observer> observers;


    public Animal(String name) {
        this.id = count++;
        this.name = name;
    }

    @Override
    public void subscribe(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void sendNotifications() {
        this.observers.forEach(observer -> observer.getNotified(this));
    }

    public String getName() {
        return name;
    }

    public abstract String describe();
}
