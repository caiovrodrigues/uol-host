package com.caio.uol.web;

import com.caio.uol.domain.Jogador;
import com.caio.uol.infra.integration.domain.VingadoresWrapper;
import com.caio.uol.service.HeroisService;
import com.caio.uol.service.JogadoresService;
import com.caio.uol.web.dto.utils.Page;
import com.caio.uol.web.dto.JogadorCreateRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
                          @RequestParam(required = false) String sort,
                          @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                          @RequestParam(required = false, defaultValue = "1") Integer pageNumber
                          ){
        Page<Jogador> jogadores = jogadoresService.listJogadores(sort, pageSize, pageNumber);
        model.addAttribute("playersPage", jogadores);
        return "players";
    }

    @PostMapping("/players")
    public String createPlayer(@Valid JogadorCreateRequest jogadorDTO, Model model){
        Jogador jogadorPersisted = jogadoresService.createJogador(jogadorDTO);
        model.addAttribute("jogador", jogadorPersisted);
        return "sucesso";
    }

    @DeleteMapping("/players/{uuid}")
    public void deletePlayer(@PathVariable String uuid){
        jogadoresService.delete(uuid);
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
