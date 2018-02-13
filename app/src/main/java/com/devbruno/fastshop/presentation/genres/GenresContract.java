package com.devbruno.fastshop.presentation.genres;

import android.support.v7.widget.RecyclerView;

import com.devbruno.fastshop.presentation.BasePresenter;
import com.devbruno.fastshop.presentation.BaseView;
import com.devbruno.fastshop.presentation.genres.adapter.GenreAdapter;
import com.devbruno.fastshop.presentation.home.HomeContract;
import com.devbruno.fastshop.presentation.home.adapter.MoviesAdapter;

/**
 * Created by bsilvabr on 12/02/2018.
 */

public class GenresContract {
    public interface View extends BaseView<GenresContract.Presenter> {
        RecyclerView getRecyclerView();
    }

    interface Presenter extends BasePresenter {
        void getGenres();

        GenreAdapter getGenreAdapter();

        void filter(String text);
    }
}
