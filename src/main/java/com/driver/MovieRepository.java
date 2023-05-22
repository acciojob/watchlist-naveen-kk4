package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    private Map<String, Movie> movies =  new HashMap<>();
    private Map<String,Director> directors =  new HashMap<>();
    private Map<String, List<String>> connections =  new HashMap<>();
    public void addMovie(Movie movie){
        movies.put(movie.getName(),movie);
    }
    public void addDirector(Director dir){
        directors.put(dir.getName(),dir);
        connections.put(dir.getName(),new ArrayList<>());
    }
    public Optional<Movie> findMovie(String mov){
        return movies.containsKey(mov)?Optional.of(movies.get(mov)):Optional.empty();
    }
    public Optional<Director> findDirector(String dir){
        return directors.containsKey(dir)?Optional.of(directors.get(dir)):Optional.empty();
    }

    public List<String> getAllMovies(String dir) {
        return connections.getOrDefault(dir,new ArrayList<>());
    }
    public void addDirectorMovies(String dir,List<String> list){
        connections.put(dir,list);
    }
    public Optional<Movie> getMovieByName(String name){
        return movies.containsKey(name)?Optional.of(movies.get(name)):Optional.empty();
    }

    public Optional<Director> getDirectorName(String name) {
        return directors.containsKey(name)?Optional.of(directors.get(name)):Optional.empty();
    }

    public List<String> getMoviesByDirectorName(String name) {
        return connections.getOrDefault(name,new ArrayList<>());
    }

    public List<String> findAllMovies() {
       return new ArrayList<>(movies.keySet());
    }
    public List<String> findAllDirectors() {
        return new ArrayList<>(directors.keySet());
    }

    public void deleteMovie(String movie) {
        movies.remove(movie);
    }

    public void deleteDirector(String name) {
        directors.remove(name);
        connections.remove(name);
    }
}
