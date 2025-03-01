package com.caio.uol.infra.db.impl;

import com.caio.uol.domain.Jogador;
import com.caio.uol.infra.db.JogadorRepository;
import com.caio.uol.service.strategy.CustomConstants;
import com.caio.uol.web.dto.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Profile({CustomConstants.local})
@Service
public class InMemoryJogadorRepository implements JogadorRepository {

    private List<Jogador> jogadores = new ArrayList<>();
    private final Logger log = LoggerFactory.getLogger(this.getClass());

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

        int totalPlayers = jogadores.size();
        int totalPages = (int) Math.ceil((double) totalPlayers / pageSize);
        int pageNumberFix = Math.min(pageNumber, totalPages);
        long skip = (long) pageSize * (Math.max(pageNumberFix - 1, 0));

        List<Jogador> jogadoresList = sortOpt
                .map(sortField -> this.jogadores.stream()
                        .sorted(Comparator.comparing((jogador) -> extractKeyToSort(jogador, sortField)))
                        .skip(skip)
                        .limit(pageSize)
                        .toList())
                .orElseGet(() -> this.jogadores.stream()
                        .skip(skip)
                        .limit(pageSize)
                        .toList());

        return new Page<>(jogadoresList, pageSize, pageNumberFix, totalPlayers, totalPages);
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
            return ((String) jogador.getClass().getDeclaredMethod(methodName.toString()).invoke(jogador)).toLowerCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
