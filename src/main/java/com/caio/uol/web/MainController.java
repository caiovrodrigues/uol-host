package com.caio.uol.web;

import com.caio.uol.infra.integration.domain.VingadoresWrapper;
import com.caio.uol.service.HeroisService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final HeroisService heroisService;

    public MainController(HeroisService heroisService){
        this.heroisService = heroisService;
    }

    @GetMapping("/liga-da-justica")
    public String hello(Model model) {
        var ligaDaJustica = heroisService.getLigaDaJustica();
        model.addAttribute("ligaDaJustica", ligaDaJustica);
        return "index";
    }

    @GetMapping("/vingadores")
    public String home(Model model){
        VingadoresWrapper vingadoresWrapper = heroisService.getVingadores();
        model.addAttribute("vingadoresWrapper", vingadoresWrapper);
        return "index";
    }

}
