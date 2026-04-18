package com.movieflix.mapper;

import com.movieflix.controller.request.MovieRequest;
import com.movieflix.controller.response.MovieResponse;
import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import com.movieflix.service.MovieService;
import lombok.experimental.UtilityClass;

import java.security.Provider;
import java.util.List;

@UtilityClass
public class MovieMapper {



    public static Movie toMovie(MovieRequest movieRequest) {

        List<Category> categories = movieRequest.categories().stream()
                .map(id -> Category.builder().id(id).build())
                .toList();

        List<Streaming> streamings = movieRequest.streamings().stream()
                .map(id -> Streaming.builder().id(id).build())
                .toList();

        return Movie.builder()
                .title(movieRequest.title())
                .description(movieRequest.description())
                .rating(movieRequest.rating())
                .releaseDate(movieRequest.releaseDate())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse (Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .createdAt(movie.getCreatedAt())
                .updatedAt(movie.getUpdatedAt())
                .categories(movie.getCategories().stream().map(CategoryMapper::toCategoryResponse).toList())
                .streamings(movie.getStreamings().stream().map(StreamingMapper::toStreamingResponse).toList())
                .build();
    }

}
