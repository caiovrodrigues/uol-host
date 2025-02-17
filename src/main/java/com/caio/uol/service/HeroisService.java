package com.caio.uol.service;

import com.caio.uol.infra.integration.HeroesHttpClientJSON;
import com.caio.uol.infra.integration.HeroesHttpClientXML;
import com.caio.uol.infra.integration.domain.LigaDaJustica;
import com.caio.uol.infra.integration.domain.VingadoresWrapper;
import org.springframework.stereotype.Service;

@Service
public class HeroisService {

    private final HeroesHttpClientJSON vingadoresHttp;
    private final HeroesHttpClientXML ligaDaJusticaHttp;

    public HeroisService(HeroesHttpClientJSON vingadoresHttp, HeroesHttpClientXML ligaDaJusticaHttp){
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
