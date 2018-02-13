package com.devbruno.fastshop.presentation;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by bsilvabr on 10/02/2018.
 */
public interface BaseView<T extends BasePresenter> {

    Context getContext();

    void setPresenter(@NonNull T presenter);

    void alertErroApi();

    void alertErroUI();

    void showLoading();

    void hideLoading();

}