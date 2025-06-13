package com.movieinfo.MovieInfo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.movieinfo.MovieInfo.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGenre(String genre);
}