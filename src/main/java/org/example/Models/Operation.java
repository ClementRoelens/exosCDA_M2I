package org.example.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Operation {
    int id;
    double amount;
    Status status;
    int accountId;

    public Operation(double amount, Status status, int accountId) {
        this.amount = amount;
        this.status = status;
        this.accountId = accountId;
    }

    public Operation(int id, double amount, Status status,int accountId) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.accountId = accountId;
    }

    public Operation(int id, double amount, Status status) {
        this.id = id;
        this.amount = amount;
        this.status = status;
    }
}
