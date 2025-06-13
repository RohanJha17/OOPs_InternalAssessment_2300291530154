package com.movieinfo.MovieInfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.movieinfo.MovieInfo.model.Movie;
import com.movieinfo.MovieInfo.service.MovieService;

@Controller
public class MovieControllerMVC {

    private MovieService service;

    public MovieControllerMVC(MovieService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/movies";
    }

    @GetMapping("/movies")
    public String getAllMovies(Model model) {
        model.addAttribute("movies", service.getAllMovies());
        return "movies"; // movies.html in templates
    }

    @GetMapping("/movies/{id}")
    public String getMovieById(@PathVariable Long id, Model model) {
        Movie movie = service.getMovieById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid movie ID: " + id));
        model.addAttribute("movie", movie);
        return "viewMovie";
    }


    @GetMapping("/movies/add")
    public String addMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "addMovie"; // addMovie.html
    }

    @PostMapping("/movies/add")
    public String addMovie(@ModelAttribute Movie movie) {
        service.saveMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/movies/update/{id}")
    public String updateMovieForm(@PathVariable Long id, Model model) {
        Movie movie = service.getMovieById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid movie ID: " + id));
        model.addAttribute("movie", movie);
        return "addMovie";
    }


    @GetMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        service.deleteMovie(id);
        return "redirect:/movies";
    }
}
