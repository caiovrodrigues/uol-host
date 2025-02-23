package com.caio.uol.infra.db.impl;

import com.caio.uol.domain.Jogador;
import com.caio.uol.infra.db.JogadorRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Profile({"prod"})
@Service
public class FileJogadorRepository implements JogadorRepository {

    @Override
    public Jogador save(Jogador jogador) {
        return null;
    }

    @Override
    public List<Jogador> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Optional<Jogador> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Jogador> findByUuid(String uuid) {
        return Optional.empty();
    }
}
