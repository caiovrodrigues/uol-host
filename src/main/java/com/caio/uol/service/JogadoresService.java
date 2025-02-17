package com.caio.uol.service;

import com.caio.uol.domain.Jogador;
import com.caio.uol.domain.enumeration.Time;
import com.caio.uol.infra.db.JogadorRepository;
import com.caio.uol.infra.integration.domain.LigaDaJustica;
import com.caio.uol.infra.integration.domain.Vingadores;
import com.caio.uol.infra.integration.domain.VingadoresWrapper;
import com.caio.uol.web.dto.JogadorCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadoresService {

    private final JogadorRepository jogadorRepository;
    private final HeroisService heroisService;

    public JogadoresService(JogadorRepository jogadorRepository, HeroisService heroisService) {
        this.jogadorRepository = jogadorRepository;
        this.heroisService = heroisService;
    }

    public List<Jogador> listJogadores(){
        return jogadorRepository.findAll();
    }

    public Jogador createJogador(JogadorCreateRequest jogadorDTO){
        Time time = Time.getTimeByName(jogadorDTO.time());
        Jogador jogador = new Jogador(jogadorDTO.nome(), null, null, null, time);
        if(Time.LIGA_DA_JUSTICA.equals(time)){
            LigaDaJustica ligaDaJustica = heroisService.getLigaDaJustica();
            List<Jogador> jogadores = listJogadores();
            List<String> codinomesJogadores = jogadores.stream().map(Jogador::getCodinome).toList();

            String codinome = ligaDaJustica.getCodinomes().stream().filter(codinomeApi -> !codinomesJogadores.contains(codinomeApi)).findFirst().orElseThrow();

            jogador.setCodinome(codinome);
        }else if(Time.VINGADORES.equals(time)){
            VingadoresWrapper vingadores = heroisService.getVingadores();
            List<Jogador> jogadores = listJogadores();
            List<String> codinomesJogadores = jogadores.stream().map(Jogador::getCodinome).toList();

            String codinome = vingadores.vingadores().stream().map(Vingadores::codinome).filter(codinomeApi -> !codinomesJogadores.contains(codinomeApi)).findFirst().orElseThrow();

            jogador.setCodinome(codinome);
        }

        return jogadorRepository.create(jogador);
    }

}
