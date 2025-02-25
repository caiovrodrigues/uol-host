package com.caio.uol.infra.db;

import com.caio.uol.domain.Jogador;
import com.caio.uol.web.dto.utils.Page;

import java.util.List;
import java.util.Optional;

public interface JogadorRepository {

    Jogador save(Jogador jogador);

    List<Jogador> findAll();

    Page<Jogador> findAll(String sort, Integer pageSize, Integer pageNumber);

    Optional<Jogador> findByName(String name);

    Optional<Jogador> findByUuid(String uuid);

    void deleteByJogador(Jogador jogador);
}
