package com.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(@Schema(type = "string", description = "Nome do filme")
                           @NotBlank(message = "O título do filme é obrigatório")
                           String title,

                           @NotEmpty(message = "A descrição do filme é opbrigatória") String description,
                           @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                           LocalDate releaseDate,
                           Double rating,

                           @Schema(type = "array", description = "Lista de códigos das categorias do filme")
                           List<Long> categories,

                           @Schema(type = "array", description = "Lista de códigos dos streamings que disponiblizam o filme")
                           List<Long> streamings) {
}
