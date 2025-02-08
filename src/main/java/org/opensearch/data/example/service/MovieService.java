package org.opensearch.data.example.service;

import org.opensearch.data.example.entity.Movie;
import org.opensearch.data.example.entity.MovieDoc;
import org.opensearch.data.example.repository.MovieElasticsearchRepository;
import org.opensearch.data.example.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Comparator.comparingInt;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    private final MovieElasticsearchRepository movieElasticsearchRepository;

    public MovieService(MovieRepository movieRepository, MovieElasticsearchRepository movieElasticsearchRepository) {
        this.movieRepository = movieRepository;
        this.movieElasticsearchRepository = movieElasticsearchRepository;
    }

    public Page<Movie> searchMovies(String query, int page, int size) {
        var pageable = PageRequest.of(page, size);
        return movieRepository.searchByQuery(query, pageable);
    }

    public Page<Movie> searchMoviesViaElastic(String searchText, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MovieDoc> searchResults = movieElasticsearchRepository.searchByQuery(searchText, pageable);

        Map<Long, Integer> idsMap = new HashMap<>();
        List<MovieDoc> movieDocs = searchResults.getContent();
        for (int i = 0; i < movieDocs.size(); i++) {
            idsMap.put(movieDocs.get(i).getId(), i);
        }

        Set<Long> ids = idsMap.keySet();

        List<Movie> moviesFromDb = movieRepository.findAllById(ids);
        moviesFromDb.sort(comparingInt(movie -> idsMap.get(movie.getId())));

        return new PageImpl<>(moviesFromDb, pageable, searchResults.getTotalElements());
    }
}