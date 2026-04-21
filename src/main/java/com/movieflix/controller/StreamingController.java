package com.movieflix.controller;

import com.movieflix.controller.request.StreamingRequest;
import com.movieflix.controller.response.StreamingResponse;
import com.movieflix.entity.Streaming;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.service.StreamingService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {

    private final StreamingService service;

    public StreamingController(StreamingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAll() {
        List<StreamingResponse> streamingResponse = service.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
        if (streamingResponse.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(streamingResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getById(@PathVariable Long id) {
        Optional<Streaming> optionalStreaming = service.findById(id);
        return optionalStreaming.map(streaming ->
                        ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<StreamingResponse> save(@Valid @RequestBody StreamingRequest streamingRequest) {
        Streaming streamingSalvo = service.save (StreamingMapper.toStreaming (streamingRequest));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(streamingSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(StreamingMapper.toStreamingResponse(streamingSalvo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
