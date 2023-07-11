package com.api.recommends.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RecommendsRecordDto(@NotBlank String name, @NotBlank String author, @NotBlank String genre, @NotNull Integer year, @NotNull byte[] image, @NotBlank String description, @NotBlank BigDecimal rating) {
}
