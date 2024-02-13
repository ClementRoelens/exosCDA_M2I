package org.example.demo_base.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("greetings")
//@Primary
public class GreetingsServiceEnglishImpl implements GreetingsService {
    @Override
    public String sayHello() {
        return "Hello everyone";
    }
}
