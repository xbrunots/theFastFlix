package com.devbruno.fastshop.presentation.movieitem;

import android.widget.ImageView;

import com.devbruno.fastshop.presentation.BasePresenter;
import com.devbruno.fastshop.presentation.BaseView;

/**
 * Created by bsilvabr on 10/02/2018.
 */

public class MovieItemContract {

    public interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void loadImageFromURL(ImageView imageView, String URL) ;

    }
}