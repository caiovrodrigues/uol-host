package com.caio.uol.web;

import com.caio.uol.domain.Jogador;
import com.caio.uol.infra.integration.domain.VingadoresWrapper;
import com.caio.uol.service.HeroisService;
import com.caio.uol.service.JogadoresService;
import com.caio.uol.web.dto.JogadorCreateRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    private final HeroisService heroisService;
    private final JogadoresService jogadoresService;

    public MainController(HeroisService heroisService, JogadoresService jogadoresService){
        this.heroisService = heroisService;
        this.jogadoresService = jogadoresService;
    }

    @GetMapping
    public String home(Model model){
        List<Jogador> jogadores = jogadoresService.listJogadores();
        model.addAttribute("jogadores", jogadores);
        return "home";
    }

    @PostMapping("/jogador")
    public String createJogador(JogadorCreateRequest jogador, Model model){
        Jogador jogadorPersisted = jogadoresService.createJogador(jogador);
        model.addAttribute("jogador", jogadorPersisted);
        return "home";
    }

    @GetMapping("/liga-da-justica")
    public String ligaDaJustica(Model model) {
        var ligaDaJustica = heroisService.getLigaDaJustica();
        model.addAttribute("ligaDaJustica", ligaDaJustica);
        return "index";
    }

    @GetMapping("/vingadores")
    public String vingadores(Model model){
        VingadoresWrapper vingadoresWrapper = heroisService.getVingadores();
        model.addAttribute("vingadoresWrapper", vingadoresWrapper);
        return "index";
    }

}
