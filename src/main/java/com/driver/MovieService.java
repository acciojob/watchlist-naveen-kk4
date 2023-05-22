package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository dao;
    public void addMovie(Movie movie){
        dao.addMovie(movie);
    }
    public void addDirector(Director dir){
        dao.addDirector(dir);
    }
    public void addMovieDirectorPair(String mov , String dir) throws NotFoundException{
        Optional<Movie> movieFound = dao.findMovie(mov);
        Optional<Director> directorFound = dao.findDirector(dir);
        if(movieFound.isEmpty() || directorFound.isEmpty())throw new NotFoundException("requested entities are not present");
       List<String> list =  dao.getAllMovies(dir);
       list.add(mov);
       dao.addDirectorMovies(dir,list);
       Director dirr = directorFound.get();
       dirr.setNumberOfMovies(dirr.getNumberOfMovies()+1);

    }
    public Movie getMovieByName(String name) throws NotFoundException{
        Optional<Movie> mov = dao.getMovieByName(name);
        if(mov.isEmpty())throw new NotFoundException("the requested movie is not present in the list");
        return mov.get();
    }


    public Director getDirectorByName(String name) throws NotFoundException {
        Optional<Director> mov = dao.getDirectorName(name);
        if(mov.isEmpty())throw new NotFoundException("the requested movie is not present in the list");
        return mov.get();
    }

    public List<String> getMoviesByDirectorName(String name) {
        return dao.getMoviesByDirectorName(name);
    }

    public List<String> findAllMovies() {
        return dao.findAllMovies();
    }

    public void deleteDirectorByName(String name) {
        List<String> movies = getMoviesByDirectorName(name);
        for(String movie : movies){
            dao.deleteMovie(movie);
        }
        dao.deleteDirector(name);
    }

    public void deleteAllDirectors() {
        List<String> dirs = dao.findAllDirectors();
        for(String dir : dirs)this.deleteDirectorByName(dir);
    }
}
