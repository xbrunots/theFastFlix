package com.devbruno.fastshop.presentation.home;

import android.support.v7.widget.RecyclerView;

import com.devbruno.fastshop.model.Genres;
import com.devbruno.fastshop.presentation.BasePresenter;
import com.devbruno.fastshop.presentation.BaseView;
import com.devbruno.fastshop.presentation.home.adapter.MoviesAdapter;
import com.devbruno.fastshop.presentation.home.adapter.MoviesStoriesAdapter;

/**
 * Created by bsilvabr on 10/02/2018.
 */

public class HomeContract {

    public interface View extends BaseView<Presenter> {
        RecyclerView getRecyclerView();

        RecyclerView getRecyclerViewEstories();

        void showSearch();

        void hideSearch();

        Genres getGenres();

        void hideGenreTitle();

        void setGenreTitle(String name);
    }

    interface Presenter extends BasePresenter {
        void getMovies();

        void getGenresDrawer();

        MoviesStoriesAdapter getMovieStoriesAdapter();

        MoviesAdapter getMovieAdapter();

        void filter(String text);

        void getShowSearch();

        void getHideSearch();
    }
}