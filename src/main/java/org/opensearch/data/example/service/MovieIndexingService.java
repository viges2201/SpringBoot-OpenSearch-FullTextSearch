package org.opensearch.data.example.service;

import org.opensearch.data.example.entity.Movie;
import org.opensearch.data.example.model.MovieDoc;
import org.opensearch.data.example.repository.MovieElasticsearchRepository;
import org.opensearch.data.example.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieIndexingService {

    private final MovieRepository movieRepository;

    private final MovieElasticsearchRepository movieElasticsearchRepository;

    public MovieIndexingService(MovieRepository movieRepository, MovieElasticsearchRepository movieElasticsearchRepository) {
        this.movieRepository = movieRepository;
        this.movieElasticsearchRepository = movieElasticsearchRepository;
    }

    @Transactional(readOnly = true)
    public void reindexAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        movieElasticsearchRepository.saveAll(
                movies.stream().map(
                        movie -> new MovieDoc(
                                movie.getId(),
                                movie.getMovie(),
                                movie.getOverview()
                        )
                ).toList()
        );
    }
}
