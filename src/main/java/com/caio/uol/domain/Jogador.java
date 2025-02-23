package com.caio.uol.domain;

import com.caio.uol.domain.enumeration.Time;

import java.util.Objects;
import java.util.UUID;

public class Jogador {
    private UUID uuid;
    private String nome;
    private String email;
    private String telefone;
    private String codinome;
    private String heroImageURL;
    private Time time;

    public Jogador(){}

    public Jogador(String nome, String email, String telefone, String codinome, String heroImageURL, Time time) {
        this.uuid = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.codinome = codinome;
        this.heroImageURL = heroImageURL;
        this.time = time;
    }

    public UUID getUuid() {
        return uuid;
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

    public String getHeroImageURL() {
        return heroImageURL;
    }

    public void setHeroImageURL(String heroImageURL){
        this.heroImageURL = heroImageURL;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return Objects.equals(uuid, jogador.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
