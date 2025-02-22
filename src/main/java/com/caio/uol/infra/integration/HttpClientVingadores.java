package com.caio.uol.infra.integration;

import com.caio.uol.infra.integration.config.FeignClientJsonConfiguration;
import com.caio.uol.infra.integration.domain.VingadoresWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${api.vingadores.value}", url = "${api.base-url}", configuration = {FeignClientJsonConfiguration.class})
public interface HttpClientVingadores {

    @RequestMapping(method = RequestMethod.GET, value = "${api.vingadores.uri}")
    VingadoresWrapper getVingadores();

}
