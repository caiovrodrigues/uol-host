package com.caio.uol.infra.db;

import com.caio.uol.domain.Jogador;

import java.util.List;

public interface JogadorRepository {

    Jogador create(Jogador jogador);

    List<Jogador> findAll();

}
