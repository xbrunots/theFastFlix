package com.devbruno.fastshop.presentation.custom;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devbruno.fastshop.R;

/**
 * Created by bsilvabr on 10/02/2018.
 */
public class CustomLoadingDialog extends Dialog implements android.view.View.OnClickListener {
    private ImageView imageViewPicBalloom;
    private ImageView imageViewLoading;
    private RelativeLayout relativeBody;
    private Activity activity;

    public CustomLoadingDialog(Activity activity) {
        super(activity);
        // TODO Auto-generated constructor stub
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.lightbox_dialog);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        init();
        setListeners();
        animAfterViews();
    }

    private void init() {
        imageViewPicBalloom = (ImageView) findViewById(R.id.imageViewLogo);
        imageViewLoading = (ImageView) findViewById(R.id.imageView);
        relativeBody = (RelativeLayout) findViewById(R.id.relative_body);

    }

    private void setListeners() {
        relativeBody.setOnClickListener(this);
    }

    private void animAfterViews() {
        imageViewPicBalloom.startAnimation(AnimationUtils.loadAnimation(imageViewPicBalloom.getContext(), R.anim.fadeloop));
        imageViewLoading.startAnimation(AnimationUtils.loadAnimation(imageViewLoading.getContext(), R.anim.rotate));
        relativeBody.startAnimation(AnimationUtils.loadAnimation(relativeBody.getContext(), R.anim.chat_move_down));
    }

    private void close() {
        dismiss();
    }

    private void animateClose() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relative_body:
                animateClose();
                break;
            default:
                break;
        }
        close();
    }

}