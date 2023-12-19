package org.example.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Operation {
    int id;
    double amount;
    Status status;
}
