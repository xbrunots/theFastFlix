package com.devbruno.fastshop.presentation.genres;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.devbruno.fastshop.R;
import com.devbruno.fastshop.infraestruture.Constants;
import com.devbruno.fastshop.infraestruture.Repository.ApiClient;
import com.devbruno.fastshop.infraestruture.Repository.ApiInterface;
import com.devbruno.fastshop.model.Genres;
import com.devbruno.fastshop.model.GenresResponse;
import com.devbruno.fastshop.presentation.genres.adapter.GenreAdapter;
import com.devbruno.fastshop.presentation.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.devbruno.fastshop.infraestruture.ConnectionUtils.isOnline;

/**
 * Created by bsilvabr on 12/02/2018.
 */

public class GenresPresenter implements GenresContract.Presenter {

    private GenresContract.View mView;
    private Context mContext;
    private HomeActivity mActivity;
    private Context context;
    ArrayList<Genres> genresArrayList;
    private GenreAdapter genreAdapter;
    private List<Genres> genres;

    @Override
    public void getGenres() {
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1);
        mView.getRecyclerView().setLayoutManager(gridLayoutManager);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<GenresResponse> call = apiService.getGenres(Constants.API_KEY);
        call.enqueue(new Callback<GenresResponse>() {
            @Override
            public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
                int statusCode = response.code();
                if (statusCode == 200 && isOnline(mContext)) {
                    Log.e(Constants.TAG_GENERIC, response.raw().toString());
                    genres = response.body().getGenres();
                    genreAdapter = new GenreAdapter(genres, R.layout.item_genre, mContext);
                    mView.getRecyclerView().setAdapter(genreAdapter);
                } else {
                    mView.alertErroApi();
                }
                mView.hideLoading();
            }

            @Override
            public void onFailure(Call<GenresResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(Constants.TAG_GENERIC, t.toString());
                mView.hideLoading();
            }
        });
    }

    @Override
    public GenreAdapter getGenreAdapter() {
        return genreAdapter;
    }

    @Override
    public void filter(String text) {

    }

    public GenresPresenter(@NonNull final GenresContract.View view) {
        mView = view;
        mContext = view.getContext();
        mActivity = (HomeActivity) mContext;
    }

    @Override
    public void start() {
        getGenres();
    }

}
