package com.devbruno.fastshop.home;

import com.devbruno.fastshop.model.Movie;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.devbruno.fastshop.presentation.home.HomePresenter.filter;
import static junit.framework.Assert.assertEquals;

/**
 * Created by bsilvabr on 12/02/2018.
 */

public class MovieFuncsTest {

    @Test
    public void testMoviesFilter() {

        List<Movie> movieList = new ArrayList<Movie>();

        Movie movie = new Movie();
        movie.setTitle("Bananas de Pijamas");
        movie.setVoteAverage(5.6);
        movie.setOriginalTitle("Bananas de Pijamas 3");

        Movie movie2 = new Movie();
        movie2.setTitle("Bananas de Pijamas");
        movie2.setVoteAverage(1.6);
        movie2.setOriginalTitle("Bananas de Pijamas 3");

        Movie movie3 = new Movie();
        movie3.setTitle("Hater de Pijamas");
        movie3.setVoteAverage(4.6);
        movie3.setOriginalTitle("Hater de Pijamas 3");

        movieList.add(movie);
        movieList.add(movie2);
        movieList.add(movie3);

        final List<Movie> filteredModelList = filter(movieList, "Bananas");

        //  esperamos 2 valores
        int expected = 2;

        // comparando
        assertEquals(expected, filteredModelList.size());
    }

}
