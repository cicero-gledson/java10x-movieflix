package com.movieflix.service;


import com.movieflix.entity.Movie;
import com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository repository;


    public Movie save(Movie movie) {
        return repository.save(movie);
    }

    public Optional<Movie> findById(Long id) {
        return repository.findById(id);
    }
    public List<Movie> findAll() {
        return repository.findAll();
    }
}
