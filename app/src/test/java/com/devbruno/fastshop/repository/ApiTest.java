package com.devbruno.fastshop.repository;

import android.util.Log;

import com.devbruno.fastshop.infraestruture.Constants;
import com.devbruno.fastshop.infraestruture.Repository.ApiClient;
import com.devbruno.fastshop.infraestruture.Repository.ApiInterface;
import com.devbruno.fastshop.model.MoviesResponse;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;

/**
 * Created by bsilvabr on 12/02/2018.
 */

public class ApiTest {


    @Test
    public void testBaseUrl() {
        assertEquals(Constants.BASE_URL, "http://api.themoviedb.org/3/");
    }

    @Test
    public void testMoviesAPI() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> call = apiService.getTopRatedMovies(Constants.API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                Log.e(Constants.TAG_GENERIC, response.raw().toString());
                int statusCode = response.code();
                assertEquals(statusCode, 200);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
            }
        });
    }
}
