package com.caio.uol.infra;

import com.caio.uol.domain.enumeration.Time;
import com.caio.uol.service.JogadoresService;
import com.caio.uol.web.dto.JogadorCreateRequest;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoaderFake {

    private final JogadoresService jogadoresService;

    public DatabaseLoaderFake(JogadoresService jogadoresService) {
        this.jogadoresService = jogadoresService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData(){
        jogadoresService.createJogador(new JogadorCreateRequest(null, "Adriana", "sousadriana.com", "550011", Time.LIGA_DA_JUSTICA.getName()));
        jogadoresService.createJogador(new JogadorCreateRequest(null, "Sandra", "sandra.com", "550011", Time.VINGADORES.getName()));

        jogadoresService.createJogador(new JogadorCreateRequest(null, "Maria", "amaria.com", "550011", Time.LIGA_DA_JUSTICA.getName()));
        jogadoresService.createJogador(new JogadorCreateRequest(null, "Juliana", "barbosajuliana.com", "550011", Time.VINGADORES.getName()));

        jogadoresService.createJogador(new JogadorCreateRequest(null, "Francisco", "silvafrancisco.com", "550011", Time.LIGA_DA_JUSTICA.getName()));
        jogadoresService.createJogador(new JogadorCreateRequest(null, "Carlos", "arlosc.com", "550011", Time.VINGADORES.getName()));

        jogadoresService.createJogador(new JogadorCreateRequest(null, "Antônio", "zeantonio.com", "550011", Time.LIGA_DA_JUSTICA.getName()));
        jogadoresService.createJogador(new JogadorCreateRequest(null, "João", "souzajoao.com", "550011", Time.VINGADORES.getName()));

        jogadoresService.createJogador(new JogadorCreateRequest(null, "Lourenço", "bialourenco.com", "550011", Time.LIGA_DA_JUSTICA.getName()));
        jogadoresService.createJogador(new JogadorCreateRequest(null, "Bruna", "bruna.com", "550011", Time.VINGADORES.getName()));
    }

}
