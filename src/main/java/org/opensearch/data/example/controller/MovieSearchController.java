package org.opensearch.data.example.controller;

import org.opensearch.data.example.entity.Movie;
import org.opensearch.data.example.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieSearchController {

    private final MovieService movieService;

    public MovieSearchController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String home() {
        return "search";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("query") String query,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Model model
    ) {
        Page<Movie> moviesPage = movieService.searchMoviesViaElastic(query, page, size);
        model.addAttribute("moviesPage", moviesPage);
        model.addAttribute("query", query);
        return "search";
    }
}