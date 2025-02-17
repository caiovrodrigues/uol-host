package com.caio.uol.infra.db.impl;

import com.caio.uol.domain.Jogador;
import com.caio.uol.infra.db.JogadorRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Profile({"local"})
@Service
public class InMemoryJogadorRepository implements JogadorRepository {

    private List<Jogador> jogadores = new ArrayList<>();

    public InMemoryJogadorRepository(){}

    @Override
    public Jogador create(Jogador jogador) {
        jogadores.add(jogador);
        return jogador;
    }

    @Override
    public List<Jogador> findAll() {
        return jogadores;
    }
}
