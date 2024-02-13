package com.example.base_spring.service;

import com.example.base_spring.model.Rabbit;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class RabbitService {
    private final Map<UUID, Rabbit> rabbits;

    public RabbitService(){
        rabbits = new HashMap<>();

        Rabbit rabbitA = Rabbit.builder()
                .id(UUID.randomUUID())
                .name("bugs bunny")
                .breed("angora")
                .build();

        Rabbit rabbitB = Rabbit.builder()
                .id(UUID.randomUUID())
                .name("lola bunny")
                .breed("bélier")
                .build();

        Rabbit rabbitC = Rabbit.builder()
                .id(UUID.randomUUID())
                .name("roger")
                .breed("jersey wooly")
                .build();

        rabbits.put(rabbitA.getId(),rabbitA);
        rabbits.put(rabbitB.getId(),rabbitB);
        rabbits.put(rabbitC.getId(),rabbitC);
    }

    public List<Rabbit> getRabbits(){
        return rabbits.values().stream().toList();
    }

    public Rabbit getRabbitById(UUID id){
        return rabbits.values().stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
      //  return rabbits.get(id);
    }
}
