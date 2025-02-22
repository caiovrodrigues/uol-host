package com.caio.uol.infra.integration;

import com.caio.uol.infra.integration.config.FeignClientXmlConfiguration;
import com.caio.uol.infra.integration.domain.LigaDaJustica;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${api.liga-da-justica.value}", url = "${api.base-url}", configuration = {FeignClientXmlConfiguration.class})
public interface HttpClientLigaJustica {

    @RequestMapping(method = RequestMethod.GET, value = "${api.liga-da-justica.uri}")
    LigaDaJustica getLigaDaJustica();
}
