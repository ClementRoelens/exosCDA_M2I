package org.example.demo_base.controller.demo_fragments;

import lombok.RequiredArgsConstructor;
import org.example.demo_base.model.Bunny;
import org.example.demo_base.service.BunnyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class DemoController {

    private final BunnyService bunnyService;

    @GetMapping
    @RequestMapping("/demo_fragments")
    public String homePage(Model model){
        List<Bunny> bunnies = bunnyService.getAllBunnies();

    }

    @GetMapping("/page_b")
    public String pageB(Model model){
        List<Bunny> bunnies = bunnyService.getAllBunnies();
        Bunny bunny = bunnies.get(0);
        model.addAttribute("idBunny", bunny.getId());
        return "demo_fragments/page_b";
    }

    @GetMapping("/detail/{bunnyId}")
    public String lapinous(@PathVariable("bunnyId") UUID id, Model model){
        Bunny bunny = bunnyService.getOneBunny(id);
        model.addAttribute("bunny", bunny);
        return "demo_fragments/page_c";
    }

}
