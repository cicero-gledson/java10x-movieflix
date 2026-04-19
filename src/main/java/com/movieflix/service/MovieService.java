package com.movieflix.service;


import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import com.movieflix.repository.MovieRepository;
import com.movieflix.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository repository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;


    public Movie save(Movie movie) {
        movie.setCategories(findCategories(movie.getCategories()));
        movie.setStreamings(findStreamings(movie.getStreamings()));
        return repository.save(movie);
    }
    private List<Category> findCategories(List<Category> moveCategories) {
        List<Category> categories = new ArrayList<>();
        moveCategories.forEach(category ->
                categoryService.findById(category.getId()).ifPresent(categories::add));
        return categories;
    }
    private List<Streaming> findStreamings(List<Streaming> moveStreamings) {
        List<Streaming> streamings = new ArrayList<>();
        moveStreamings.forEach(streaming ->
                streamingService.findById(streaming.getId()).ifPresent(streamings::add));
        return streamings;
    }


    public Optional<Movie> findById(Long id) {
        return repository.findById(id);
    }
    public List<Movie> findAll() {
        return repository.findAll();
    }

    public Optional<Movie> update(Long id, Movie updateMovie) {
        Optional<Movie> optMovie = repository.findById(id);
        if (optMovie.isPresent()) {
            Movie movie = optMovie.get();
            movie.setTitle(updateMovie.getTitle());
            movie.setDescription(updateMovie.getDescription());
            movie.setRating(updateMovie.getRating());
            movie.setReleaseDate(updateMovie.getReleaseDate());

            movie.getCategories().clear();
            movie.getCategories().addAll(findCategories(updateMovie.getCategories()));

            movie.getStreamings().clear();
            movie.getStreamings().addAll(findStreamings(updateMovie.getStreamings()));

            repository.save(movie);
            return Optional.of(movie);
        }
        return Optional.empty();
    }

    public List<Movie> findByCategory(Long category) {
        return repository.findByCategories_Id(category);
    }

    public void delete(Long id) {
        if (! repository.existsById(id))
            throw new ResourceNotFoundException("Id não encontrado: " + id);

        repository.deleteById(id);


    }
}
