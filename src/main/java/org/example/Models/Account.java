package org.example.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Account {
    int id;
    Client client;
    int clientId;
    double balance;
    List<Operation> operations;

    public Account(int clientId) {
        this.clientId = clientId;
        this.operations = new ArrayList<>();
    }

    public Account(int id, int clientId, double balance) {
        this.id = id;
        this.clientId = clientId;
        this.balance = balance;
        this.operations = new ArrayList<>();
    }

    public Account(int id, Client client, double balance) {
        this.id = id;
        this.client = client;
        this.balance = balance;
        this.operations = new ArrayList<>();
    }
}
