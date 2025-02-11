package com.caio.uol.infra.db.impl;

import com.caio.uol.infra.db.DataRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile({"local"})
@Repository
public class InMemoryData implements DataRepository {
    @Override
    public String createUser() {
        return "InMemoryData está criando um usuário";
    }

    @Override
    public String listUsers() {
        return "InMemoryData está listando usuários";
    }
}
