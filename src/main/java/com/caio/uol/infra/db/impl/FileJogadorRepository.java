package com.caio.uol.infra.db.impl;

import com.caio.uol.domain.Jogador;
import com.caio.uol.infra.db.JogadorRepository;
import com.caio.uol.service.strategy.CustomConstants;
import com.caio.uol.web.dto.utils.Page;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Profile({CustomConstants.prod})
@Service
public class FileJogadorRepository implements JogadorRepository {

    private final String PATH_TO_PLAYERS_ARCHIVE = "src/main/resources/static/players.ser";
    private final File file = new File(PATH_TO_PLAYERS_ARCHIVE);

    public FileJogadorRepository() throws Exception {
        if (!file.exists()) {
            try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))){
                objectOutputStream.writeObject(new ArrayList<Jogador>());
            }
        }
    }

    @Override
    public Jogador save(Jogador jogador) {
        List<Jogador> playersFromFile = findAll();

        int indexOfJogadorInList = playersFromFile.indexOf(jogador);

        if(indexOfJogadorInList != -1){
            playersFromFile.set(indexOfJogadorInList, jogador);
        }else{
            playersFromFile.add(jogador);
        }

        try(ObjectOutputStream objectOutputStream = getOutputStream()){
            objectOutputStream.writeObject(playersFromFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jogador;
    }

    @Override
    public List<Jogador> findAll() {
        try(ObjectInputStream objectInputStream = getInputStream()){
            return (List<Jogador>) objectInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Page<Jogador> findAll(String sort, Integer pageSize, Integer pageNumber) {
        List<Jogador> jogadores = findAll();

        Optional<String> sortOpt = Optional.ofNullable(sort);

        int totalPlayers = jogadores.size();
        int totalPages = (int) Math.ceil((double) totalPlayers / pageSize);
        int pageNumberFix = Math.min(pageNumber, totalPages);
        long skip = (long) pageSize * (Math.max(pageNumberFix - 1, 0));

        List<Jogador> jogadoresList = sortOpt
                .map(sortField -> jogadores.stream()
                        .sorted(Comparator.comparing((jogador) -> extractKeyToSort(jogador, sortField)))
                        .skip(skip)
                        .limit(pageSize)
                        .toList())
                .orElseGet(() -> jogadores.stream()
                        .skip(skip)
                        .limit(pageSize)
                        .toList());

        return new Page<>(jogadoresList, pageSize, pageNumberFix, totalPlayers, totalPages);
    }

    @Override
    public Optional<Jogador> findByName(String name) {
        List<Jogador> jogadores = findAll();
        return jogadores.stream().filter(jogador -> jogador.getNome().equals(name)).findFirst();
    }

    @Override
    public Optional<Jogador> findByUuid(String uuid) {
        List<Jogador> jogadores = findAll();
        return jogadores.stream().filter(jogador -> jogador.getUuid().toString().equals(uuid)).findFirst();
    }

    @Override
    public void deleteByJogador(Jogador jogador) {
        //do nothing
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

    private ObjectOutputStream getOutputStream(){
        try {
            return new ObjectOutputStream(new FileOutputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObjectInputStream getInputStream(){
        try {
            return new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
