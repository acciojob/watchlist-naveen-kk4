package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService service;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        service.addMovie(movie);
        return new ResponseEntity<>("movie added successfully", HttpStatus.CREATED);
    }
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director dir){
        service.addDirector(dir);
        return new ResponseEntity<>("director added successfully", HttpStatus.CREATED);
    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movie , @RequestParam String director){
        try{
            service.addMovieDirectorPair(movie,director);
            return new ResponseEntity<>("movie has been successfully mapped with the given director",HttpStatus.CREATED);
        }
        catch(NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
        try{
            Movie mov = service.getMovieByName(name);
            return new ResponseEntity<>(mov,HttpStatus.OK);
        }
        catch(NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
        try{
            Director mov = service.getDirectorByName(name);
            return new ResponseEntity<>(mov,HttpStatus.OK);
        }
        catch(NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String name){
        List<String> ans = service.getMoviesByDirectorName(name);
        return new ResponseEntity<>(ans, HttpStatus.OK);
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> list = service.findAllMovies();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String name){
        service.deleteDirectorByName(name);
        return new ResponseEntity<>(name+" and his movies deleted successfully",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        service.deleteAllDirectors();
        return new ResponseEntity<>("all directors and their movies taken off from watchlist",HttpStatus.OK);

    }




}
