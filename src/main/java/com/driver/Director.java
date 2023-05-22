package com.driver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Director {
    String name;
    int numberOfMovies;
    double imdbRating;

    public Director() {

    }

    public Director(String name, int numberOfMovies, double imdbRating) {
        this.name = name;
        this.numberOfMovies = numberOfMovies;
        this.imdbRating = imdbRating;
    }
}
