package com.caio.uol.domain;

import com.caio.uol.domain.enumeration.Time;
import com.caio.uol.web.dto.JogadorCreateRequest;

public class Jogador {

    private String nome;
    private String email;
    private String telefone;
    private String codinome;
    private Time time;

    public Jogador(){}

    public Jogador(JogadorCreateRequest jogadorDto){
        this.nome = jogadorDto.nome();

    }

    public Jogador(String nome, String email, String telefone, String codinome, Time time) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.codinome = codinome;
        this.time = time;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCodinome() {
        return codinome;
    }

    public void setCodinome(String codinome) {
        this.codinome = codinome;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
