package com.movieflix.controller;

import com.movieflix.controller.request.MovieRequest;
import com.movieflix.controller.response.MovieResponse;
import com.movieflix.entity.Movie;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.service.MovieService;
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
public class MovieController {
    private final MovieService service;

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest movieRequest) {
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
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @RequestBody MovieRequest movieRequest) {
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
