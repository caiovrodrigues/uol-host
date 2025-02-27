package com.caio.uol.domain.enumeration;

import com.caio.uol.service.strategy.CustomConstants;

import java.util.Arrays;

public enum Time {

    VINGADORES("Os Vingadores", "vingadores.json", CustomConstants.VingadoresProviderName),
    LIGA_DA_JUSTICA("Liga da Justiça", "liga_da_justica.xml", CustomConstants.LdjProviderName);

    String name;
    String path;
    String beanName;

    Time(String name, String path, String beanName){
        this.name = name;
        this.path = path;
        this.beanName = beanName;
    }

    public String getName() {
        return name;
    }

    public String getBeanName() {
        return beanName;
    }

    public static Time getTimeByName(String name){
        return Arrays.stream(Time.values()).filter(time -> time.getName().equals(name)).findFirst().orElse(Time.VINGADORES);
    }

}
