package com.caio.uol.domain.enumeration;

public enum Grupo {

    VINGADORES("Os Vingadores", "liga_da_justica.xml"),
    LIGA_DA_JUSTICA("Liga da Justiça", "vingadores.json");

    String name;
    String path;

    Grupo(String name, String path){
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

}
