package com.caio.uol.infra.integration;

import com.caio.uol.infra.integration.config.FeignClientXmlConfiguration;
import com.caio.uol.infra.integration.domain.LigaDaJustica;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "heroesHttpClientXML", url = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/", configuration = {FeignClientXmlConfiguration.class})
public interface HeroesHttpClientXML {

    @RequestMapping(method = RequestMethod.GET, value = "/liga_da_justica.xml")
    LigaDaJustica getLigaDaJustica();
}
