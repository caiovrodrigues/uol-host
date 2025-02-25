package com.caio.uol.web.dto.utils;

import java.util.Collections;
import java.util.List;

public record Page<T>(
        List<T> content,
        Integer pageSize,
        Integer pageNumber,
        Integer totalElements,
        Integer totalPages
) {

    public Page() {
        this(Collections.emptyList(), 0, 0,0,0);
    }
}
