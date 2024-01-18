package com.praise.push.application.port.in;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ReadKeywordsQuery(
        @PositiveOrZero(message = "keyword size must be at least zero.")
        @NotNull(message = "keyword size must not be null.")
        Integer size
) {
}
