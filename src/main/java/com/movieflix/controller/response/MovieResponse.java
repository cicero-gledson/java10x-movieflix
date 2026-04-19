package com.movieflix.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieflix.entity.Category;
import com.movieflix.entity.Streaming;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MovieResponse(
        Long id,
        String title,
        String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,
        Double rating,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<CategoryResponse> categories,
        List<StreamingResponse> streamings
) {
}
