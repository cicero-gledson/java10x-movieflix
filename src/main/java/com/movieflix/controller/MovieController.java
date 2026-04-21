package com.movieflix.controller;

import com.movieflix.controller.docInterface.MovieControllerDoc;
import com.movieflix.controller.request.MovieRequest;
import com.movieflix.controller.response.MovieResponse;
import com.movieflix.entity.Movie;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController implements MovieControllerDoc {
    private final MovieService service;

    @PostMapping
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest movieRequest) {
        Movie movie = MovieMapper.toMovie(movieRequest);
        movie = service.save(movie);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(movie.getId()).toUri();
        return ResponseEntity.created(uri).body(MovieMapper.toMovieResponse(movie));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll() {
        List<MovieResponse> movieResponses = service.findAll()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        if (movieResponses.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(movieResponses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest movieRequest) {
        return service.update(id, MovieMapper.toMovie(movieRequest))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> search(@RequestParam Long category) {
        List<MovieResponse> movieResponses = service.findByCategory(category)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        if (movieResponses.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(movieResponses);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
