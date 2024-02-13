package org.example.demo_base.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Bunny {
    private UUID id;
    private String name;
    private String breed;



}
