package org.example.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Client {
    int id;
    String firstName;
    String lastname;
    List<Account> accounts;
}
