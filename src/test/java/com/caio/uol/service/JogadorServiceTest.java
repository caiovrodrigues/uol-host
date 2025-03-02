package com.caio.uol.service;

import com.caio.uol.domain.Jogador;
import com.caio.uol.domain.enumeration.Time;
import com.caio.uol.infra.db.JogadorRepository;
import com.caio.uol.web.dto.JogadorCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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


    @Test
    void editJogador_ExistingJogador_thenEdit(){
        //given
        UUID existingJogadorUuid = UUID.randomUUID();
        Jogador existingJogador = new Jogador();
        existingJogador.setUuid(existingJogadorUuid);
        existingJogador.setNome("Caio");
        existingJogador.setEmail("caio@gmail.com");
        existingJogador.setTelefone("8888-4444");
        existingJogador.setTime(Time.VINGADORES);

        JogadorCreateRequest jogadorRequest = new JogadorCreateRequest(existingJogadorUuid.toString(), "Caio V", "caiov@gmail.com", "4444-8888", Time.VINGADORES.getName());
        when(jogadorRepository.findByUuid(existingJogadorUuid.toString()))
                .thenReturn(Optional.of(existingJogador));
        when(jogadorRepository.save(Mockito.any(Jogador.class))).thenAnswer(
                (i) -> i.getArguments()[0]);

        //when
        Jogador actualJogador = jogadoresService.editJogador(jogadorRequest);

        // Then
        assertNotNull(actualJogador);

        ArgumentCaptor<Jogador> jogadorCaptor = ArgumentCaptor.forClass(Jogador.class);
        verify(jogadorRepository, times(1)).save(jogadorCaptor.capture());

        Jogador savedJogador = jogadorCaptor.getValue();
        assertEquals(existingJogadorUuid, savedJogador.getUuid());
        assertEquals(jogadorRequest.nome(), savedJogador.getNome());
        assertEquals(jogadorRequest.email(), savedJogador.getEmail());
        assertEquals(jogadorRequest.telefone(), savedJogador.getTelefone());
        assertEquals(jogadorRequest.time(), savedJogador.getTime().getName());
    }



























}
