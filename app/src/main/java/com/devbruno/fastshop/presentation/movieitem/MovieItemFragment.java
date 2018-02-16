package com.devbruno.fastshop.presentation.movieitem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.devbruno.fastshop.R;
import com.devbruno.fastshop.infraestruture.Constants;
import com.devbruno.fastshop.infraestruture.DateUtils;
import com.devbruno.fastshop.infraestruture.SharedUtils;
import com.devbruno.fastshop.model.Movie;
import com.devbruno.fastshop.presentation.BaseFragment;
import com.devbruno.fastshop.presentation.custom.CustomLoadingDialog;
import com.devbruno.fastshop.presentation.custom.CustomPayDialog;
import com.devbruno.fastshop.presentation.custom.CustomRateView;

/**
 * Created by bsilvabr on 10/02/2018.
 */

public class MovieItemFragment extends BaseFragment implements MovieItemContract.View {
    private MovieItemActivity mActivity;
    private MovieItemContract.Presenter mPresenter;
    private CustomLoadingDialog customLoadingDialog = null;
    private Movie movies;
    private ImageView imageViewFoto, imageViewBg;
    private ImageButton imageViewButtonBack, imageViewButtonShare, imageViewButtonCast;
    private TextView textViewTitulo, textViewDesc, textViewDate, textViewTitulo2, textViewRate;
    private CustomRateView customRateView;
    private Button buttonPay, buttonRet;
    private CustomPayDialog customPayDialog;

    public static MovieItemFragment newInstance(CharSequence screenTitle) {
        MovieItemFragment fragment = new MovieItemFragment();

        Bundle args = new Bundle();
        args.putCharSequence(Constants.ARGUMENT_TITLE, screenTitle);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MovieItemActivity) context;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_item, container, false);

        Intent intent = getActivity().getIntent();
        movies = (Movie) intent.getSerializableExtra(Constants.ARGUMENT_MOVIES);
        initViews(rootView);
        renderModel();
        setListeners();
        mPresenter.start();

        return rootView;
    }

    private void initViews(View rootView) {

        imageViewFoto = (ImageView) rootView.findViewById(R.id.imageViewPoster);
        imageViewBg = (ImageView) rootView.findViewById(R.id.imageViewBackground);
        imageViewButtonBack = (ImageButton) rootView.findViewById(R.id.imageButton);
        imageViewButtonCast = (ImageButton) rootView.findViewById(R.id.imageButtonCast);
        imageViewButtonShare = (ImageButton) rootView.findViewById(R.id.imageButton2);

        buttonPay = (Button) rootView.findViewById(R.id.button);
        buttonRet = (Button) rootView.findViewById(R.id.button2);

        customRateView = (CustomRateView) rootView.findViewById(R.id.customrateview);

        textViewTitulo2 = (TextView) rootView.findViewById(R.id.textView5);
        textViewTitulo = (TextView) rootView.findViewById(R.id.textView2);
        textViewDate = (TextView) rootView.findViewById(R.id.textView3);
        textViewDesc = (TextView) rootView.findViewById(R.id.textView4);
        textViewRate = (TextView) rootView.findViewById(R.id.textView6);
        customPayDialog = new CustomPayDialog(mActivity);
    }

    private void renderModel() {
        textViewTitulo.setText(movies.getTitle());
        textViewTitulo2.setText(movies.getTitle());
        textViewDate.setText(DateUtils.convertDateENtoBR(movies.getReleaseDate()));
        textViewDesc.setText(movies.getOverview());

        mPresenter.loadImageFromURL(imageViewFoto, movies.getPosterPath());
        mPresenter.loadImageFromURL(imageViewBg, movies.getBackdropPath());

        int valReal = getIntRateValueReal(movies.getVoteAverage().toString());

        textViewRate.setText(movies.getVoteAverage().toString());
        if (valReal > 1) {
            customRateView.setRateValue(valReal / 2);
        } else {
            customRateView.setRateValue(1);
        }
        imageViewBg.startAnimation(AnimationUtils.loadAnimation(imageViewBg.getContext(), R.anim.zoomin));
        imageViewFoto.startAnimation(AnimationUtils.loadAnimation(imageViewFoto.getContext(), R.anim.fade_in));
        buttonRet.startAnimation(AnimationUtils.loadAnimation(imageViewBg.getContext(), R.anim.fade_in));
        buttonPay.startAnimation(AnimationUtils.loadAnimation(imageViewBg.getContext(), R.anim.fade_in));

    }

    public int getIntRateValueReal(String rateValue) {
        double d = Double.parseDouble(rateValue);
        int i = (int) d;
        return i;
    }

    private void setListeners() {
        imageViewButtonBack.setOnClickListener(onClickListenerImageButtons);
        imageViewButtonCast.setOnClickListener(onClickListenerImageButtons);
        imageViewButtonShare.setOnClickListener(onClickListenerImageButtons);

        buttonRet.setOnClickListener(onClickListenerImageButtons);
        buttonPay.setOnClickListener(onClickListenerImageButtons);

        imageViewButtonShare.setOnClickListener(onClickListenerImageButtons);
        imageViewButtonShare.setOnClickListener(onClickListenerImageButtons);
    }

    private View.OnClickListener onClickListenerImageButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageButton: //onBackPress
                    getActivity().onBackPressed();
                    break;
                case R.id.button: //aluguel
                    customPayDialog.show();
                case R.id.button2:
                    customPayDialog.show();
                    break;
                case R.id.imageButton2:
                    imageViewFoto.buildDrawingCache();
                    Bitmap bitmap = imageViewFoto.getDrawingCache();
                    SharedUtils.shareIt(getContext(), movies, bitmap);
                    break;
                case R.id.imageButtonCast:

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
    public void setPresenter(@NonNull MovieItemContract.Presenter presenter) {
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


}
