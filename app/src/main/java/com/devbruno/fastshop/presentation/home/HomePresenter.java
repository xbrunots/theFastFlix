package com.devbruno.fastshop.presentation.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.devbruno.fastshop.R;
import com.devbruno.fastshop.infraestruture.Repository.ApiClient;
import com.devbruno.fastshop.infraestruture.Repository.ApiInterface;
import com.devbruno.fastshop.infraestruture.Constants;
import com.devbruno.fastshop.model.Movie;
import com.devbruno.fastshop.model.MoviesResponse;
import com.devbruno.fastshop.presentation.genres.GenresFragment;
import com.devbruno.fastshop.presentation.home.adapter.MoviesAdapter;
import com.devbruno.fastshop.presentation.home.adapter.MoviesStoriesAdapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.devbruno.fastshop.infraestruture.ConnectionUtils.isOnline;

/**
 * Created by bsilvabr on 10/02/2018.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;
    private Context mContext;
    private HomeActivity mActivity;
    private MoviesAdapter moviesAdapter;
    private MoviesStoriesAdapter moviesAdapterStories;
    private final Comparator<Movie> mComparator = null;
    private List<Movie> movies;
    ArrayList<Movie> defaultList;

    public HomePresenter(@NonNull final HomeContract.View view) {
        mView = view;
        mContext = view.getContext();
        mActivity = (HomeActivity) mContext;
    }

    @Override
    public void start() {
        mView.showLoading();
        getMovies();
    }

    public static List<Movie> filter(List<Movie> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<Movie> filteredModelList = new ArrayList<>();
        for (Movie model : models) {
            final String text = model.getTitle().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    public void getGenresDrawer() {
        mActivity.initFragment(new GenresFragment(), Constants.GENRES_NAME, mActivity);
    }

    @Override
    public void getMovies() {
        mView.hideGenreTitle();
        if (mView.getRecyclerView() == null) {
            mView.hideLoading();
            mView.alertErroUI();
            return;
        }
        if (Constants.API_KEY.isEmpty()) {
            mView.hideLoading();
            mView.alertErroApi();
            return;
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        mView.getRecyclerView().setLayoutManager(gridLayoutManager);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mView.getRecyclerViewEstories().setLayoutManager(layoutManager);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(Constants.API_KEY);

        if (mView.getGenres() != null) {
            call = apiService.getMoviesForGenres(mView.getGenres().getId(), Constants.API_KEY);
            mView.setGenreTitle(mView.getGenres().getName().toUpperCase());
        }

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                Log.e(Constants.TAG_GENERIC, response.raw().toString());
                int statusCode = response.code();
                if(statusCode==200 && isOnline(mContext)){
                    movies = response.body().getResults();
                    defaultList = new ArrayList<>();
                    defaultList.addAll(movies);
                    moviesAdapter = new MoviesAdapter(movies, R.layout.list_item_movie, mContext, mComparator);
                    moviesAdapterStories = new MoviesStoriesAdapter(movies, R.layout.list_item_around, mContext, mComparator);
                    mView.getRecyclerView().setAdapter(moviesAdapter);
                    mView.getRecyclerViewEstories().setAdapter(moviesAdapterStories);

                    moviesAdapter.notifyDataSetChanged();
                    moviesAdapterStories.notifyDataSetChanged();
                }else{
                    mView.alertErroApi();
                }
                mView.hideLoading();
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(Constants.TAG_GENERIC, t.toString());
                mView.hideLoading();
            }
        });
    }

    @Override
    public void filter(String text) {
        text = text.toLowerCase();
        if (text.isEmpty()) {
            try {
                moviesAdapterStories.setFilter(defaultList, defaultList);
                moviesAdapter.setFilter(defaultList, defaultList);
                mView.getRecyclerView().setAdapter(moviesAdapter);
                mView.getRecyclerViewEstories().setAdapter(moviesAdapterStories);
                moviesAdapter.notifyDataSetChanged();
                moviesAdapterStories.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ArrayList<Movie> newList = new ArrayList<>();
            for (Movie mMovies : movies) {
                String movieName = mMovies.getTitle().toLowerCase();
                if (movieName.contains(text)) {
                    newList.add(mMovies);
                }
            }
            moviesAdapter.setFilter(newList, defaultList);
            mView.getRecyclerViewEstories().setAdapter(moviesAdapter);
            moviesAdapter.notifyDataSetChanged();

            try {
                moviesAdapterStories.setFilter(newList, defaultList);
                mView.getRecyclerViewEstories().setAdapter(moviesAdapterStories);
                moviesAdapterStories.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void getShowSearch() {
        mView.showSearch();
    }

    @Override
    public void getHideSearch() {
        mView.hideSearch();
    }

    @Override
    public MoviesAdapter getMovieAdapter() {
        return moviesAdapter;
    }

    @Override
    public MoviesStoriesAdapter getMovieStoriesAdapter() {
        return moviesAdapterStories;
    }


}
