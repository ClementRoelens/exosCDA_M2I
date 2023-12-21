package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventCustomer {
    private int customerId;
    private int eventId;
}
