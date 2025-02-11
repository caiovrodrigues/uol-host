package com.caio.uol.infra.db.impl;

import com.caio.uol.infra.db.DataRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile({"prod"})
@Repository
public class FileData implements DataRepository {
    @Override
    public String createUser() {
        return "File Data está criando um usuário";
    }

    @Override
    public String listUsers() {
        return "File Data está listando usuários";
    }
}
