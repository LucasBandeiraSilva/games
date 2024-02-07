package com.lucas.bandeira.games.games.record.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Year;

public record GamesRecordDto(@NotBlank String name, @NotNull Integer release_year,@NotBlank String shortDescription, @NotBlank String image) {
}
