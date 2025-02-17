package com.caio.uol.domain.enumeration;

import java.util.Arrays;

public enum Time {

    VINGADORES("Os Vingadores", "liga_da_justica.xml"),
    LIGA_DA_JUSTICA("Liga da Justiça", "vingadores.json");

    String name;
    String path;

    Time(String name, String path){
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public static Time getTimeByName(String name){
        return Arrays.stream(Time.values()).filter(time -> time.getName().equals(name)).findFirst().orElse(Time.VINGADORES);
    }

}
