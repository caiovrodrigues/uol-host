package com.caio.uol.infra.db;

import com.caio.uol.domain.Jogador;

import java.util.List;
import java.util.Optional;

public interface JogadorRepository {

    Jogador save(Jogador jogador);

    List<Jogador> findAll();

    Optional<Jogador> findByName(String name);

    Optional<Jogador> findByUuid(String uuid);
}
