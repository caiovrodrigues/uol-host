package com.caio.uol.infra;

import com.caio.uol.domain.Jogador;
import com.caio.uol.domain.enumeration.Time;
import com.caio.uol.infra.db.JogadorRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoaderFake {

    private final JogadorRepository jogadorRepository;

    public DatabaseLoaderFake(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData(){
//        jogadorRepository.create(new Jogador("Caio", "mail.com", "550011", "Teste", "https://cdn-icons-png.flaticon.com/512/2748/2748558.png", Time.VINGADORES));
    }

}
