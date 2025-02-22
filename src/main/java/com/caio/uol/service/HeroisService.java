package com.caio.uol.service;

import com.caio.uol.infra.integration.HttpClientVingadores;
import com.caio.uol.infra.integration.HttpClientLigaJustica;
import com.caio.uol.infra.integration.domain.LigaDaJustica;
import com.caio.uol.infra.integration.domain.VingadoresWrapper;
import org.springframework.stereotype.Service;

@Service
public class HeroisService {

    private final HttpClientVingadores vingadoresHttp;
    private final HttpClientLigaJustica ligaDaJusticaHttp;

    public HeroisService(HttpClientVingadores vingadoresHttp, HttpClientLigaJustica ligaDaJusticaHttp){
        this.vingadoresHttp = vingadoresHttp;
        this.ligaDaJusticaHttp = ligaDaJusticaHttp;
    }

    public VingadoresWrapper getVingadores(){
        return vingadoresHttp.getVingadores();
    }

    public LigaDaJustica getLigaDaJustica(){
        return ligaDaJusticaHttp.getLigaDaJustica();
    }

}
