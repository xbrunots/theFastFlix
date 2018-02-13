package com.devbruno.fastshop.models;

import com.devbruno.fastshop.model.Movie;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MoviesTest {

    @Test
    public void testSetPosterPath() {
        // Implementa método de teste unitário: Nome
        Movie movie1 = new Movie();
        movie1.setPosterPath("/imagem.jpg");
        assertEquals("/imagem.jpg", movie1.getPosterPath());
    }

    @Test
    public void testSetTitle() {
        // Implementa método de teste unitário: Nome
        Movie movie1 = new Movie();
        movie1.setTitle("Pocahontas");
        assertEquals("Pocahontas", movie1.getTitle());
    }

    @Test
    public void testSetoriginalTitle() {
        // Implementa método de teste unitário: Nome
        Movie movie1 = new Movie();
        movie1.setOriginalTitle("Mega Man 4");
        assertEquals("Mega Man 4", movie1.getOriginalTitle());
    }

    @Test
    public void testSetoverview() {
        // Implementa método de teste unitário: Nome
        Movie movie1 = new Movie();
        movie1.setOverview("Lorem Ipsum dolus Lorem Ipsum dolus Lorem Ipsum dolus Lorem Ipsum dolus Lorem Ipsum dolus ");
        assertEquals("Lorem Ipsum dolus Lorem Ipsum dolus Lorem Ipsum dolus Lorem Ipsum dolus Lorem Ipsum dolus ", movie1.getOverview());
    }

}
