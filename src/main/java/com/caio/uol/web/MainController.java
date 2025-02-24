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
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public String players(Model model,
                          @RequestParam(required = false) String nome,
                          @RequestParam(required = false) Integer pageSize,
                          @RequestParam(required = false) Integer pageNumber
                          ){
        List<Jogador> jogadores = jogadoresService.listJogadores(nome, pageSize, pageNumber);
        model.addAttribute("jogadores", jogadores);
        return "players";
    }

    @PostMapping("/players")
    public String createPlayer(@Valid JogadorCreateRequest jogadorDTO, Model model, HttpServletResponse response) throws IOException {
        Jogador jogadorPersisted = jogadoresService.createJogador(jogadorDTO);
        model.addAttribute("jogador", jogadorPersisted);
        return "sucesso";
    }

    @DeleteMapping("/players/{uuid}")
    public void deletePlayer(@PathVariable String uuid){
        jogadoresService.delete(uuid);
        System.out.println("Deletado com sucesso: " + uuid);
    }

    @PostMapping("/players/edit")
    public String editPlayer(@Valid JogadorCreateRequest jogadorDTO, Model model){
        Jogador jogador = jogadoresService.editJogador(jogadorDTO);
        model.addAttribute("jogador", jogador);
        return "sucesso";
    }

    @GetMapping("/cadastro")
    public String cadastro(){
        return "cadastro";
    }

    @GetMapping("/cadastro/edit/{uuidPlayer}")
    public String editPlayer(Model model, @PathVariable String uuidPlayer){
        Jogador jogador = jogadoresService.findJogadorByUuid(uuidPlayer);
        model.addAttribute("playerToEdit", jogador);
        return "cadastro";
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
