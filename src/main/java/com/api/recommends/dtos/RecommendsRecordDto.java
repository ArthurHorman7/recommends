package com.api.recommends.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecommendsRecordDto(@NotBlank String name, @NotBlank String image, @NotBlank String author, @NotBlank String genre, @NotNull Integer year, @NotBlank String description, @NotNull Float rating) {
}
