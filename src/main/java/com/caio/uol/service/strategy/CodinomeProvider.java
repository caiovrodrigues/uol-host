package com.caio.uol.service.strategy;

import com.caio.uol.domain.enumeration.Time;

public interface CodinomeProvider {

    String getCodinome(Time time);
}
