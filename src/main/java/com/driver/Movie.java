package com.driver;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
     String name;
     int durationInMinutes;
     double imdbRating;

    public Movie() {

    }

    public Movie(String name, int durationInMinutes, double imdbRating) {
        this.name = name;
        this.durationInMinutes = durationInMinutes;
        this.imdbRating = imdbRating;
    }
}
