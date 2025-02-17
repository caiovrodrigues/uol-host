package com.caio.uol.infra.integration.domain;

import java.util.List;

public class LigaDaJustica{
    public Codinomes codinomes;

    public LigaDaJustica() {
    }

    public LigaDaJustica(Codinomes codinomes) {
        this.codinomes = codinomes;
    }

    public List<String> getCodinomes() {
        return codinomes.getCodinome();
    }
}
