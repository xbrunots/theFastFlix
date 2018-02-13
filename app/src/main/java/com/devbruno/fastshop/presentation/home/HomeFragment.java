package com.devbruno.fastshop.presentation.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.devbruno.fastshop.R;
import com.devbruno.fastshop.infraestruture.Constants;
import com.devbruno.fastshop.infraestruture.ScreenUtils;
import com.devbruno.fastshop.model.Genres;
import com.devbruno.fastshop.presentation.BaseFragment;
import com.devbruno.fastshop.presentation.custom.CustomLoadingDialog;

/**
 * Created by bsilvabr on 10/02/2018.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    private HomeActivity mActivity;
    private HomeContract.Presenter mPresenter;
    private RecyclerView recyclerView, recyclerViewEstories;
    private CustomLoadingDialog customLoadingDialog = null;
    private EditText editTextFinder;
    private ImageButton imageButtonDrawerGenres, imageButtonDrawerGenres2, imageButtonLupa, imageButtonSair;
    private LinearLayout main;
    private LinearLayout linearLayoutShowSearch, linearLayoutHideSearch;
    private Genres genres;
    private TextView genreTitle;

    public static HomeFragment newInstance(CharSequence screenTitle) {
        HomeFragment fragment = new HomeFragment();

        Bundle args = new Bundle();
        args.putCharSequence(Constants.ARGUMENT_TITLE, screenTitle);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (HomeActivity) context;

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getMovies();
    }

    public Genres getGenres() {
        return genres;
    }

    @Override
    public void setGenreTitle(String name) {
        genreTitle.setText(name);
        genreTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideGenreTitle() {
        genreTitle.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        Intent intent = getActivity().getIntent();
        if (intent.getExtras() != null) {
            genres = (Genres) intent.getSerializableExtra(Constants.ARGUMENT_GENRES);
        } else {
            genres = null;
        }

        initViews(rootView);
        setListeners();
        return rootView;
    }

    private void initViews(View rootView) {
        imageButtonLupa = rootView.findViewById(R.id.imageButtonLupa);
        imageButtonSair = rootView.findViewById(R.id.imageButtonClose);
        recyclerView = rootView.findViewById(R.id.movies_recycler_view);
        recyclerViewEstories = rootView.findViewById(R.id.movies_top_recycler_view);
        linearLayoutShowSearch = rootView.findViewById(R.id.linearFindEnable);
        linearLayoutHideSearch = rootView.findViewById(R.id.linearFindDisable);
        main = rootView.findViewById(R.id.linearMain);
        imageButtonDrawerGenres = rootView.findViewById(R.id.imageButton);
        imageButtonDrawerGenres2 = rootView.findViewById(R.id.imageButton1);
        editTextFinder = rootView.findViewById(R.id.editTextFinder);
        genreTitle = rootView.findViewById(R.id.textViewGenreTitle);
        editTextFinder.clearFocus();
        ScreenUtils.hideSoftKeyboard(getActivity());
        editTextFinder.setFocusableInTouchMode(false);
        editTextFinder.setFocusable(false);
        editTextFinder.setFocusableInTouchMode(true);
        editTextFinder.setFocusable(true);
        hideSearch();
    }

    private void setListeners() {
        imageButtonDrawerGenres.setOnClickListener(onClickListener);
        imageButtonDrawerGenres2.setOnClickListener(onClickListener);
        imageButtonSair.setOnClickListener(onClickListenerFinder);
        imageButtonLupa.setOnClickListener(onClickListenerFinder);
        editTextFinder.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
                mPresenter.filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
                mPresenter.filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.getGenresDrawer();
        }
    };

    private View.OnClickListener onClickListenerFinder = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageButtonLupa:
                    mPresenter.getShowSearch();
                    break;
                case R.id.imageButtonClose:
                    mPresenter.getHideSearch();
                    break;
                default:
                    return;
            }
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setPresenter(@NonNull HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void alertErroApi() {
        Toast.makeText(getActivity(), Constants.ERROR_API, Toast.LENGTH_LONG).show();
    }

    @Override
    public void alertErroUI() {
        Toast.makeText(getActivity(), Constants.ERROR_UI, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void showLoading() {
        if (customLoadingDialog == null) {
            customLoadingDialog = new CustomLoadingDialog(getActivity());
        }
        customLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        if (customLoadingDialog != null) {
            customLoadingDialog.dismiss();
        }
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public RecyclerView getRecyclerViewEstories() {
        return recyclerViewEstories;
    }

    @Override
    public void showSearch() {
        linearLayoutShowSearch.clearAnimation();
        linearLayoutHideSearch.clearAnimation();
        imageButtonSair.clearAnimation();
        imageButtonDrawerGenres.clearAnimation();
        linearLayoutHideSearch.setVisibility(View.GONE);
        linearLayoutShowSearch.setVisibility(View.VISIBLE);
        linearLayoutShowSearch.startAnimation(AnimationUtils.loadAnimation(linearLayoutShowSearch.getContext(), R.anim.movedown));
        imageButtonSair.startAnimation(AnimationUtils.loadAnimation(imageButtonLupa.getContext(), R.anim.move_to_left));
        imageButtonDrawerGenres.startAnimation(AnimationUtils.loadAnimation(imageButtonLupa.getContext(), R.anim.move_to_right));

        editTextFinder.setFocusable(true);
        editTextFinder.requestFocus();
    }

    @Override
    public void hideSearch() {
        linearLayoutShowSearch.clearAnimation();
        linearLayoutHideSearch.clearAnimation();
        imageButtonLupa.clearAnimation();
        imageButtonDrawerGenres2.clearAnimation();
        linearLayoutHideSearch.setVisibility(View.VISIBLE);
        linearLayoutShowSearch.setVisibility(View.GONE);
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        linearLayoutHideSearch.startAnimation(AnimationUtils.loadAnimation(linearLayoutHideSearch.getContext(), R.anim.movedown));
        imageButtonLupa.startAnimation(AnimationUtils.loadAnimation(imageButtonLupa.getContext(), R.anim.move_to_left));
        imageButtonDrawerGenres2.startAnimation(AnimationUtils.loadAnimation(imageButtonLupa.getContext(), R.anim.move_to_right));

        editTextFinder.setText("");
    }

}
