package com.caio.uol.infra;

public class GroupHeroesNotAvailable extends RuntimeException {

    public GroupHeroesNotAvailable(String time){
        super(time);
    }

}