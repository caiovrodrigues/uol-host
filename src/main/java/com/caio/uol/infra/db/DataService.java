package com.caio.uol.infra.db;

import com.caio.uol.infra.integration.domain.LigaDaJustica;
import com.caio.uol.infra.integration.domain.VingadoresWrapper;

public interface DataService {

    VingadoresWrapper createUser();

    LigaDaJustica listUsers();

}
