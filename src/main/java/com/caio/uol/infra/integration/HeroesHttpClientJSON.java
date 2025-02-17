package com.caio.uol.infra.integration;

import com.caio.uol.infra.integration.config.FeignClientJsonConfiguration;
import com.caio.uol.infra.integration.domain.VingadoresWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "heroesHttpClientJSON", url = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/", configuration = {FeignClientJsonConfiguration.class})
public interface HeroesHttpClientJSON {

    @RequestMapping(method = RequestMethod.GET, value = "/vingadores.json")
    VingadoresWrapper getVingadores();

}
