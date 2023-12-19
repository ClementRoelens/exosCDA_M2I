package org.example.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Account {
    int id;
    Client client;
    double balance;
    List<Operation> operations;
}
