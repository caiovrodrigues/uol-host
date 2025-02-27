package com.caio.uol.service.strategy.impl;

import com.caio.uol.domain.Jogador;
import com.caio.uol.domain.enumeration.Time;
import com.caio.uol.infra.GroupHeroesNotAvailable;
import com.caio.uol.infra.db.JogadorRepository;
import com.caio.uol.service.HeroisService;
import com.caio.uol.service.strategy.CodinomeProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

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
        Stream<String> allCodinomesApi = heroisService.getLigaDaJustica().getCodinomes().stream();
        List<String> allCodinomesEscolhidos = jogadorRepository.findAll().stream().map(Jogador::getCodinome).toList();

        return allCodinomesApi.filter(codinome -> !allCodinomesEscolhidos.contains(codinome)).findFirst().orElseThrow(() -> new GroupHeroesNotAvailable(Time.LIGA_DA_JUSTICA.getName()));
    }
}
