package com.devbruno.fastshop.presentation.home.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.devbruno.fastshop.R;
import com.devbruno.fastshop.infraestruture.Constants;
import com.devbruno.fastshop.infraestruture.ImageUtil.ImageDownloadAndCache;
import com.devbruno.fastshop.model.Movie;
import com.devbruno.fastshop.presentation.custom.CustomRoundRectImageView;
import com.devbruno.fastshop.presentation.home.HomeActivity;
import com.devbruno.fastshop.presentation.models.MovieItemActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MoviesStoriesAdapter extends RecyclerView.Adapter<MoviesStoriesAdapter.MovieViewHolder> {

    private final HomeActivity mActivity;
    public List<Movie> movies;
    private int rowLayout;
    private Context context;
    ArrayList<Movie> movieArrayList;
    ArrayList<Movie> defaultMovieArrayList;

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        CustomRoundRectImageView poster_redondo;


        public MovieViewHolder(View v) {
            super(v);
            poster_redondo = (CustomRoundRectImageView) v.findViewById(R.id.imageViewPoster2);
        }
    }

    public MoviesStoriesAdapter(List<Movie> movies, int rowLayout, Context context, Comparator<Movie> comparator) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
        this.mActivity = (HomeActivity) context;
    }

    @Override
    public MoviesStoriesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    public void add(Movie item, int position) {
        movies.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Movie item) {
        int position = movies.indexOf(item);
        movies.remove(position);
        notifyItemRemoved(position);
    }

    public int getPosition(Movie item) {
        return movies.indexOf(item);
    }

    public void setFilter(List<Movie> newList, List<Movie> defaultList) throws Exception {
        defaultMovieArrayList = new ArrayList<>();
        defaultMovieArrayList.addAll(defaultList);
        movieArrayList = new ArrayList<>();
        movieArrayList.addAll(newList);
        movies.clear();
        if (newList.size() < 1) {
            movies.addAll(defaultMovieArrayList);
        } else {
            movies.addAll(movieArrayList);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, MovieItemActivity.class);
                intent.putExtra(Constants.ARGUMENT_MOVIES, movies.get(position));
                mActivity.startActivity(intent);

            }
        };

        new ImageDownloadAndCache.DownloadImageTask(holder.poster_redondo,
                mActivity).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                Constants.BASE_POSTER_URL.concat(movies.get(position).getPosterPath().toString()));
        holder.poster_redondo.startAnimation(AnimationUtils.loadAnimation(holder.poster_redondo.getContext(), R.anim.movedown));

        holder.poster_redondo.setOnClickListener(onClickListener);
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }
}