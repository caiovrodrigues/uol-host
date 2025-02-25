package com.caio.uol.infra.db.impl;

import com.caio.uol.domain.Jogador;
import com.caio.uol.infra.db.JogadorRepository;
import com.caio.uol.web.dto.utils.Page;
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
    public Page<Jogador> findAll(String sort, Integer pageSize, Integer pageNumber) {
        Optional<String> sortOpt = Optional.ofNullable(sort);

        List<Jogador> jogadoresList = sortOpt
                .map(sortName -> this.jogadores.stream()
                        .sorted(Comparator.comparing((jogador) -> extractKeyToSort(jogador, sortName)))
                        .skip((long) pageSize * (pageNumber - 1))
                        .limit(pageSize)
                        .toList())
                .orElseGet(() -> this.jogadores.stream().skip((long) pageSize * (pageNumber - 1)).limit(pageSize).toList());

        int totalPages = (int) Math.ceil((double) jogadores.size() / pageSize);
        return new Page<>(jogadoresList, pageSize, pageNumber, totalPages);
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

    private String extractKeyToSort(Jogador jogador, String sortKey){
        StringBuilder methodName = new StringBuilder();
        methodName.append("get");
        methodName.append(sortKey);
        methodName.setCharAt(3, Character.toUpperCase(sortKey.charAt(0)));
        try {
            return (String) jogador.getClass().getDeclaredMethod(methodName.toString()).invoke(jogador);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
