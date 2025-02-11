package com.caio.uol.web;

import com.caio.uol.infra.db.DataRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final DataRepository dataRepository;

    public MainController(DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    @GetMapping("/")
    public String hello(Model model){
        String user = dataRepository.createUser();
        String listUsers = dataRepository.listUsers();
        model.addAttribute("user", user);
        model.addAttribute("listUsers", listUsers);
        return "index";
    }

}
