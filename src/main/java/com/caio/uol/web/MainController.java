package com.caio.uol.web;

import com.caio.uol.domain.Jogador;
import com.caio.uol.infra.integration.domain.VingadoresWrapper;
import com.caio.uol.service.HeroisService;
import com.caio.uol.service.JogadoresService;
import com.caio.uol.web.dto.JogadorCreateRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class MainController {

    private final HeroisService heroisService;
    private final JogadoresService jogadoresService;

    public MainController(HeroisService heroisService, JogadoresService jogadoresService){
        this.heroisService = heroisService;
        this.jogadoresService = jogadoresService;
    }

    @GetMapping
    public void home(HttpServletResponse response) throws Exception{
        response.sendRedirect("players");
    }

    @GetMapping("/players")
    public String players(Model model){
        List<Jogador> jogadores = jogadoresService.listJogadores();
        model.addAttribute("jogadores", jogadores);
        return "players";
    }

    @GetMapping("/cadastro")
    public String cadastro(){
        return "cadastro";
    }

    @PostMapping("/jogador")
    public void createJogador(@Valid JogadorCreateRequest jogador, Model model, HttpServletResponse response) throws Exception{
        Jogador jogadorPersisted = jogadoresService.createJogador(jogador);
        List<Jogador> jogadores = jogadoresService.listJogadores();
        model.addAttribute("jogador", jogadorPersisted);
        model.addAttribute("jogadores", jogadores);
        response.sendRedirect("");
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
