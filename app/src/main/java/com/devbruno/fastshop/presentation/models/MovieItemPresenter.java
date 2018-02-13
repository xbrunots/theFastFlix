package com.devbruno.fastshop.presentation.models;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.devbruno.fastshop.infraestruture.Constants;
import com.devbruno.fastshop.infraestruture.ImageUtil.ImageDownloadAndCache;

/**
 * Created by bsilvabr on 10/02/2018.
 */

public class MovieItemPresenter implements MovieItemContract.Presenter {

    private MovieItemContract.View mView;
    private Context mContext;
    private Activity mActivity;

    public MovieItemPresenter(@NonNull final MovieItemContract.View view) {
        mView = view;
        mContext = view.getContext();
        mActivity = (MovieItemActivity) mContext;
    }

    @Override
    public void start() {

    }

    @Override
    public void loadImageFromURL(ImageView imageView, String URL) {
        new ImageDownloadAndCache.DownloadImageTask(imageView,
                mActivity).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                Constants.BASE_POSTER_URL.concat(URL));

    }
}
