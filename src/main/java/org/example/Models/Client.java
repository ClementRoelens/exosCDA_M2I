package org.example.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Client {
    int id;
    String firstName;
    String lastname;
    List<Account> accounts;

    public Client(String firstName, String lastname) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.accounts = new ArrayList<>();
    }

    public Client(int id, String firstName, String lastname) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
        this.accounts = new ArrayList<>();
    }

    public Client(int id, String firstName, String lastname, List<Account> accounts) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
        this.accounts = accounts;
    }


    public void addAccount(Account account){
        this.accounts.add(account);
    }
}
