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
        jogadorRepository.save(new Jogador("Adriana", "sousadriana.com", "550011", "Lanterna Verde", "https://cdn-icons-png.flaticon.com/512/2748/2748558.png", Time.LIGA_DA_JUSTICA));
        jogadorRepository.save(new Jogador("Sandra", "sandra.com", "550011", "Hulk", "https://cdn-icons-png.flaticon.com/512/2748/2748558.png", Time.VINGADORES));
        jogadorRepository.save(new Jogador("Maria", "amaria.com", "550011", "Flash", "https://cdn-icons-png.flaticon.com/512/2748/2748558.png", Time.LIGA_DA_JUSTICA));
        jogadorRepository.save(new Jogador("Juliana", "barbosajuliana.com", "550011", "Capitão América", "https://cdn-icons-png.flaticon.com/512/2748/2748558.png", Time.VINGADORES));
        jogadorRepository.save(new Jogador("Francisco", "silvafrancisco.com", "550011", "Aquaman", "https://cdn-icons-png.flaticon.com/512/2748/2748558.png", Time.LIGA_DA_JUSTICA));
        jogadorRepository.save(new Jogador("Carlos", "arlosc.com", "550011", "Pantera Negra", "https://cdn-icons-png.flaticon.com/512/2748/2748558.png", Time.VINGADORES));
        jogadorRepository.save(new Jogador("Antônio", "zeantonio.com", "550011", "Batman", "https://cdn-icons-png.flaticon.com/512/2748/2748558.png", Time.LIGA_DA_JUSTICA));
        jogadorRepository.save(new Jogador("João", "souzajoao.com", "550011", "Homem de Ferro", "https://cdn-icons-png.flaticon.com/512/2748/2748558.png", Time.VINGADORES));
        jogadorRepository.save(new Jogador("Lourenço", "bialourenco.com", "550011", "Visão", "https://cdn-icons-png.flaticon.com/512/2748/2748558.png", Time.VINGADORES));
        jogadorRepository.save(new Jogador("Bruna", "bruna.com", "550011", "Feiticeira Escarlate", "https://cdn-icons-png.flaticon.com/512/2748/2748558.png", Time.VINGADORES));
    }

}
