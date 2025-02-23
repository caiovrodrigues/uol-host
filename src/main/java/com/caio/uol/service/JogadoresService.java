package com.caio.uol.service;

import com.caio.uol.domain.Jogador;
import com.caio.uol.domain.enumeration.Time;
import com.caio.uol.infra.db.JogadorRepository;
import com.caio.uol.service.strategy.CodinomeProvider;
import com.caio.uol.web.dto.JogadorCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JogadoresService {

    private final JogadorRepository jogadorRepository;

    private final Map<String, CodinomeProvider> codinomeProvider;

    public JogadoresService(JogadorRepository jogadorRepository, Map<String, CodinomeProvider> codinomeProvider) {
        this.jogadorRepository = jogadorRepository;
        this.codinomeProvider = codinomeProvider;
    }

    public List<Jogador> listJogadores(){
        return jogadorRepository.findAll();
    }

    public Jogador createJogador(JogadorCreateRequest jogadorDTO){
        Time time = Time.getTimeByName(jogadorDTO.time());
        String codinome = codinomeProvider.get(time.getBeanName()).getCodinome(time);
        Jogador jogador = new Jogador(jogadorDTO.nome(), jogadorDTO.email(), jogadorDTO.telefone(), codinome, time);

        return jogadorRepository.create(jogador);
    }

}
