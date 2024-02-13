package org.example.demo_base.controller.demo_fragments;

import lombok.RequiredArgsConstructor;
import org.example.demo_base.model.Bunny;
import org.example.demo_base.service.BunnyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v0/demo")
@RequiredArgsConstructor
public class DemoRestController {
    private final BunnyService bunnyService;

    @GetMapping
    public List<Bunny> getAllBunnies(){
        return bunnyService.getAllBunnies();
    }
}
