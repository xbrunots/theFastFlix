package com.devbruno.fastshop.infraestruture.Repository;

import com.devbruno.fastshop.model.Genres;
import com.devbruno.fastshop.model.GenresResponse;
import com.devbruno.fastshop.model.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);


    @GET("/3/genre/{id}/movies")
    Call<MoviesResponse> getMoviesForGenres(@Path("id") int id, @Query("api_key") String apiKey);


    @GET("/3/genre/movie/list")
    Call<GenresResponse> getGenres(@Query("api_key") String apiKey);

}


