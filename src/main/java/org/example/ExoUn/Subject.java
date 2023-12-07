package org.example.ExoUn;

public interface Subject {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void sendNotifications();
}
