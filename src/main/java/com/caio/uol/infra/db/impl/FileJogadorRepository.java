package com.caio.uol.infra.db.impl;

import com.caio.uol.domain.Jogador;
import com.caio.uol.infra.db.JogadorRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile({"prod"})
@Service
public class FileJogadorRepository implements JogadorRepository {

    @Override
    public Jogador create(Jogador jogador) {
        return null;
    }

    @Override
    public List<Jogador> findAll() {
        return null;
    }
}
