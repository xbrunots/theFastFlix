package com.devbruno.fastshop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bsilvabr on 12/02/2018.
 */

public class GenresResponse {

    @SerializedName("genres")
    private List<Genres> genres;

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

}
