package com.caio.uol.infra.integration.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

public class Codinomes {

    @JacksonXmlElementWrapper(useWrapping = false)
    public List<String> codinome;

    public Codinomes() {
    }

    public Codinomes(List<String> codinome) {
        this.codinome = codinome;
    }

    public List<String> getCodinome() {
        return codinome;
    }
}
