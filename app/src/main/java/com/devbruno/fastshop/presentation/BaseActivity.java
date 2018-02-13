package com.devbruno.fastshop.presentation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.devbruno.fastshop.R;
import com.devbruno.fastshop.presentation.genres.GenresContract;
import com.devbruno.fastshop.presentation.genres.GenresPresenter;
import com.devbruno.fastshop.presentation.home.HomeActivity;
import com.devbruno.fastshop.presentation.home.HomeContract;
import com.devbruno.fastshop.presentation.home.HomePresenter;
import com.devbruno.fastshop.presentation.models.MovieItemContract;
import com.devbruno.fastshop.presentation.models.MovieItemPresenter;

/**
 * Created by bsilvabr on 10/02/2018.
 */

public class BaseActivity extends AppCompatActivity {
    public static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof HomeContract.View) {
            HomeContract.View view = (HomeContract.View) fragment;
            view.setPresenter(new HomePresenter(view));
        } else if (fragment instanceof MovieItemContract.View) {
            MovieItemContract.View view = (MovieItemContract.View) fragment;
            view.setPresenter(new MovieItemPresenter(view));
        } else if (fragment instanceof GenresContract.View) {
            GenresContract.View view = (GenresContract.View) fragment;
            view.setPresenter(new GenresPresenter(view));
        }
    }

    protected <F extends BaseFragment> boolean hasCurrentFragment(int containerId) {
        return getCurrentFragment(containerId, BaseFragment.class) != null;
    }

    protected <F extends BaseFragment> F getCurrentFragment(int containerId, Class<F> fragmentClass) {
        return (F) getSupportFragmentManager().findFragmentById(containerId);
    }

    protected <F extends BaseFragment> void setCurrentFragment(int containerId, F fragment) {
        getSupportFragmentManager().beginTransaction().replace(containerId, fragment, fragment.getClass().getSimpleName()).commit();
    }

    public BaseActivity() {

    }

    @SuppressLint("ResourceType")
    public void initFragment(Fragment fragment, String tag, Activity activity) {
        final android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        final android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left,
                R.anim.slide_in_left, R.anim.slide_out_left)
                .add(R.id.frame_contaner, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

}
