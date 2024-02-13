package org.example.demo_base.service;

import org.example.demo_base.model.Bunny;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BunnyService {
    private final Map<UUID, Bunny> bunnies;


    public BunnyService() {
        bunnies = new HashMap<>();
        Bunny bunny = Bunny.builder()
                .id(UUID.randomUUID())
                .name("Boule de poils")
                .breed("Oreilles droites")
                .build();
        Bunny bunny2 = Bunny.builder()
                .id(UUID.randomUUID())
                .name("Caramel")
                .breed("Bélier")
                .build();
        Bunny bunny3 = Bunny.builder()
                .id(UUID.randomUUID())
                .name("Exampeel")
                .breed("Tête de lion")
                .build();
        bunnies.put(bunny.getId(),bunny);
        bunnies.put(bunny2.getId(),bunny2);
        bunnies.put(bunny3.getId(),bunny3);
    }

    public List<Bunny> getAllBunnies(){
        return bunnies.values().stream().toList();
    }

    public Bunny getOneBunny(UUID id){
        return bunnies.get(id);
    }
}
