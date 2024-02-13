package org.example.demo_base.service;

import org.springframework.stereotype.Service;

@Service("salutations")
public class GreetingsServiceFrenchImpl implements GreetingsService {
    @Override
    public String sayHello() {
        return "Bonjour tout le monde";
    }
}
