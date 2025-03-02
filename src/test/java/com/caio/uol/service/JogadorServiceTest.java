package com.caio.uol.service;

import com.caio.uol.domain.Jogador;
import com.caio.uol.infra.db.JogadorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

public class JogadorServiceTest {

    @Mock
    private JogadorRepository jogadorRepository;

    @InjectMocks
    private JogadoresService jogadoresService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findJogadorByUuid_ExistingUuid_ReturnsJogador(){
        //given
        UUID uuid = UUID.randomUUID();
        Jogador expectedJogador = new Jogador();
        expectedJogador.setUuid(uuid);
        when(jogadorRepository.findByUuid(uuid.toString()))
                .thenReturn(Optional.of(expectedJogador));

        //when
        Jogador actualJogador = jogadoresService.findJogadorByUuid(uuid.toString());

        //then
        assertEquals(expectedJogador, actualJogador);
        verify(jogadorRepository, times(1))
                .findByUuid(uuid.toString());
    }

    @Test
    void findJogadorByName_ExistingName_ReturnsJogador() {
        //given
        String name = "Caio";
        Jogador expectedJogador = new Jogador();
        expectedJogador.setNome(name);
        when(jogadorRepository.findByName(name))
                .thenReturn(Optional.of(expectedJogador));

        //when
        Jogador actualJogador = jogadoresService.findJogadorByName(name);

        //then
        assertEquals(expectedJogador, actualJogador);
        verify(jogadorRepository, times(1)).findByName(name);
    }

}
