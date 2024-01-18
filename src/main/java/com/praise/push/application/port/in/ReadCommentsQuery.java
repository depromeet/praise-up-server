package com.praise.push.application.port.in;


import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Objects;

public record ReadCommentsQuery(
        @PositiveOrZero(message = "page must be at least zero.")
        Integer page,
        @Positive(message = "page size must not be less than one.")
        Integer size
) {
    public ReadCommentsQuery {
        if (Objects.isNull(page)) page = 0;
        if (Objects.isNull(size)) size = 24;
    }
}
