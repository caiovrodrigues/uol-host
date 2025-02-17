package com.caio.uol.infra.integration.config;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import feign.Logger;
import feign.Request;
import feign.Util;
import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;

public class FeignClientXmlConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options options(){
        return new Request.Options();
    }

    @Bean
    public Decoder decoder(){
        return (response, type) -> {
            String bodyString = Util.toString(response.body().asReader(Util.UTF_8));
            JavaType javaType = TypeFactory.defaultInstance().constructType(type);
            return new XmlMapper().readValue(bodyString, javaType);
        };
    }

}

