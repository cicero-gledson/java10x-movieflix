package com.movieflix.service;


import com.movieflix.entity.Streaming;
import com.movieflix.repository.StreamingRepository;
import com.movieflix.service.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreamingService {
    private final StreamingRepository repository;

    public StreamingService(StreamingRepository streamingRepository) {
        this.repository = streamingRepository;
    }

    public List<Streaming> findAll() {
        return repository.findAll();
    }

    public Streaming save(Streaming streaming) {
        return repository.save(streaming);
    }

    public Optional<Streaming> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Id não encontrado: " + id);
        }
        repository.deleteById(id);
    }

}
