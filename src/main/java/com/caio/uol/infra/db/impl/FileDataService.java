package com.caio.uol.infra.db.impl;

import com.caio.uol.infra.db.DataService;
import com.caio.uol.infra.integration.domain.LigaDaJustica;
import com.caio.uol.infra.integration.domain.VingadoresWrapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"prod"})
@Service
public class FileDataService implements DataService {
    @Override
    public VingadoresWrapper createUser() {
        return null;
    }

    @Override
    public LigaDaJustica listUsers() {
        return null;
    }
}
