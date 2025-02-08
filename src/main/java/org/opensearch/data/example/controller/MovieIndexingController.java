package org.opensearch.data.example.controller;

import org.opensearch.data.example.service.MovieIndexingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/indexing")
public class MovieIndexingController {

    private final MovieIndexingService movieIndexingService;

    public MovieIndexingController(MovieIndexingService movieIndexingService) {
        this.movieIndexingService = movieIndexingService;
    }

    @GetMapping("/reindex")
    public String reindexMovies() {
        movieIndexingService.reindexAllMovies();
        return "Переиндексация завершена!";
    }
}
