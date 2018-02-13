package com.devbruno.fastshop.presentation.genres.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.devbruno.fastshop.R;
import com.devbruno.fastshop.infraestruture.Constants;
import com.devbruno.fastshop.model.Genres;
import com.devbruno.fastshop.presentation.home.HomeActivity;

import java.util.List;

/**
 * Created by bsilvabr on 12/02/2018.
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreAdapterViewHolder> {

    private final HomeActivity mActivity;
    public List<Genres> genres;
    private int rowLayout;
    private Context context;

    public class GenreAdapterViewHolder extends RecyclerView.ViewHolder {
        LinearLayout genreLayout;
        Button textView;


        public GenreAdapterViewHolder(View v) {
            super(v);
            genreLayout = (LinearLayout) v.findViewById(R.id.layoutItem);
            textView = (Button) v.findViewById(R.id.txtValue);
        }
    }

    public GenreAdapter(List<Genres> genres, int rowLayout, Context context) {
        this.genres = genres;
        this.rowLayout = rowLayout;
        this.context = context;
        this.mActivity = (HomeActivity) context;
    }

    @Override
    public GenreAdapter.GenreAdapterViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new GenreAdapter.GenreAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GenreAdapter.GenreAdapterViewHolder holder, final int position) {

        holder.textView.setText(String.valueOf(genres.get(position).getName()));

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, HomeActivity.class);
                intent.putExtra(Constants.ARGUMENT_GENRES, genres.get(position));
                mActivity.startActivity(intent);
            }
        };
        holder.genreLayout.setOnClickListener(onClickListener);
        holder.textView.setOnClickListener(onClickListener);
    }


    @Override
    public int getItemCount() {
        return genres.size();
    }

}