package com.caio.uol.service.strategy.impl;

import com.caio.uol.domain.Jogador;
import com.caio.uol.domain.enumeration.Time;
import com.caio.uol.infra.db.JogadorRepository;
import com.caio.uol.infra.integration.domain.LigaDaJustica;
import com.caio.uol.service.HeroisService;
import com.caio.uol.service.strategy.CodinomeProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LDJCodinomeProvider implements CodinomeProvider {

    private final HeroisService heroisService;

    private final JogadorRepository jogadorRepository;

    public LDJCodinomeProvider(HeroisService heroisService, JogadorRepository jogadorRepository) {
        this.heroisService = heroisService;
        this.jogadorRepository = jogadorRepository;
    }

    @Override
    public String getCodinome(Time time) {
        LigaDaJustica allHeroesLDJ = heroisService.getLigaDaJustica();
        List<String> codinomesJaEscolhidos = jogadorRepository.findAll().stream().map(Jogador::getCodinome).toList();

       return allHeroesLDJ.getCodinomes().stream().filter(codinomeApi -> !codinomesJaEscolhidos.contains(codinomeApi)).findFirst().orElseThrow();
    }
}
