package com.caio.uol.infra.db.impl;

import com.caio.uol.infra.db.DataService;
import com.caio.uol.infra.integration.domain.LigaDaJustica;
import com.caio.uol.infra.integration.domain.VingadoresWrapper;
import com.caio.uol.service.HeroisService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"local"})
@Service
public class InMemoryDataService implements DataService {

    private final HeroisService heroisService;

    public InMemoryDataService(HeroisService heroisService){
        this.heroisService = heroisService;
    }

    @Override
    public VingadoresWrapper createUser() {
        return heroisService.getVingadores();
    }

    @Override
    public LigaDaJustica listUsers() {
        return heroisService.getLigaDaJustica();
    }
}
