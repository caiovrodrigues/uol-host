package com.caio.uol.service.strategy.impl;

import com.caio.uol.domain.Jogador;
import com.caio.uol.domain.enumeration.Time;
import com.caio.uol.infra.db.JogadorRepository;
import com.caio.uol.infra.integration.domain.Vingadores;
import com.caio.uol.infra.integration.domain.VingadoresWrapper;
import com.caio.uol.service.HeroisService;
import com.caio.uol.service.strategy.CodinomeProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VingadoresCodinomeProvider implements CodinomeProvider {

    private final HeroisService heroisService;

    private final JogadorRepository jogadorRepository;

    public VingadoresCodinomeProvider(HeroisService heroisService, JogadorRepository jogadorRepository) {
        this.heroisService = heroisService;
        this.jogadorRepository = jogadorRepository;
    }

    @Override
    public String getCodinome(Time time) {
        VingadoresWrapper allHeroesVinga = heroisService.getVingadores();
        List<String> codinomesJaEscolhidos = jogadorRepository.findAll().stream().map(Jogador::getCodinome).toList();

        return allHeroesVinga.vingadores().stream().map(Vingadores::codinome).filter(codinomeApi -> !codinomesJaEscolhidos.contains(codinomeApi)).findFirst().orElseThrow();
    }
}
