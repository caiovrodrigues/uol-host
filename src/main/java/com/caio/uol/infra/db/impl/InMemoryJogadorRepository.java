package com.caio.uol.infra.db.impl;

import com.caio.uol.domain.Jogador;
import com.caio.uol.infra.db.JogadorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Profile({"local"})
@Service
public class InMemoryJogadorRepository implements JogadorRepository {

    private List<Jogador> jogadores = new ArrayList<>();
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public InMemoryJogadorRepository(){}

    @Override
    public Jogador save(Jogador jogador) {
        int indexOfJogadorInList = jogadores.indexOf(jogador);

        if(indexOfJogadorInList != -1){
            log.info("""
                Jogador: "%s";
                isAddMode = %s
                """.formatted(jogador.getNome(), true));
            jogadores.set(indexOfJogadorInList, jogador);
            return jogador;
        }

        log.info("""
                Jogador: "%s";
                isAddMode = %s
                """.formatted(jogador.getNome(), false));

        jogadores.add(jogador);
        return jogador;
    }

    @Override
    public List<Jogador> findAll() {
        return jogadores;
    }

    @Override
    public List<Jogador> findAll(String nome, Integer pageSize, Integer pageNumber) {
        Optional<String> nomeOpt = Optional.ofNullable(nome);
        Optional<Integer> pageSizeOpt = Optional.ofNullable(pageSize);
        Optional<Integer> pageNumberOpt = Optional.ofNullable(pageNumber);

        if(pageSizeOpt.isPresent() && pageNumberOpt.isPresent())
            return jogadores.stream().skip((long) pageSize * pageNumber).limit(pageSize).toList();

        if(pageSizeOpt.isPresent())
            return jogadores.stream().limit(pageSize).toList();

        return jogadores;
    }

    @Override
    public Optional<Jogador> findByName(String name) {
        return jogadores.stream().filter(jogador -> jogador.getNome().equals(name)).findFirst();
    }

    @Override
    public Optional<Jogador> findByUuid(String uuid) {
        return jogadores.stream().filter(jogador -> jogador.getUuid().toString().equals(uuid)).findFirst();
    }

    @Override
    public void deleteByJogador(Jogador jogador) {
        jogadores.remove(jogador);
    }
}
