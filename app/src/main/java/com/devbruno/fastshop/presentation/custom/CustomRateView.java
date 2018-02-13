package com.devbruno.fastshop.presentation.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.devbruno.fastshop.R;

/**
 * Created by bsilvabr on 11/02/2018.
 */

public class CustomRateView extends RelativeLayout {

    private ImageView star1, star2, star3, star4, star5;
    private Context context;
    private int rateValue = 0;

    public CustomRateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.rateview, this);

        star1 = findViewById(R.id.star_one);
        star2 = findViewById(R.id.star_two);
        star3 = findViewById(R.id.star_three);
        star4 = findViewById(R.id.star_four);
        star5 = findViewById(R.id.star_five);
        TypedArray props = context.obtainStyledAttributes(attrs, R.styleable.CustomRateViewAttribs);
        setRateValue(props.getInt(R.styleable.CustomRateViewAttribs_rateValue, rateValue));
        setupView();
    }

    private void setupView() {
        star1.startAnimation(AnimationUtils.loadAnimation(star1.getContext(), R.anim.chat_move_down));
        star2.startAnimation(AnimationUtils.loadAnimation(star2.getContext(), R.anim.chat_move_down1));
        star3.startAnimation(AnimationUtils.loadAnimation(star3.getContext(), R.anim.chat_move_down2));
        star4.startAnimation(AnimationUtils.loadAnimation(star4.getContext(), R.anim.chat_move_down3));
        star5.startAnimation(AnimationUtils.loadAnimation(star5.getContext(), R.anim.chat_move_down4));
        refresRate();
    }

    private void refresRate() {
        if (rateValue == 0) {
            setStatusStars(star1, false);
            setStatusStars(star2, false);
            setStatusStars(star3, false);
            setStatusStars(star4, false);
            setStatusStars(star5, false);
        } else if (rateValue == 1) {
            setStatusStars(star1, true);
            setStatusStars(star2, false);
            setStatusStars(star3, false);
            setStatusStars(star4, false);
            setStatusStars(star5, false);
        } else if (rateValue == 2) {
            setStatusStars(star1, true);
            setStatusStars(star2, true);
            setStatusStars(star3, false);
            setStatusStars(star4, false);
            setStatusStars(star5, false);
        } else if (rateValue == 3) {
            setStatusStars(star1, true);
            setStatusStars(star2, true);
            setStatusStars(star3, true);
            setStatusStars(star4, false);
            setStatusStars(star5, false);
        } else if (rateValue == 4) {
            setStatusStars(star1, true);
            setStatusStars(star2, true);
            setStatusStars(star3, true);
            setStatusStars(star4, true);
            setStatusStars(star5, false);
        } else if (rateValue == 5) {
            setStatusStars(star1, true);
            setStatusStars(star2, true);
            setStatusStars(star3, true);
            setStatusStars(star4, true);
            setStatusStars(star5, true);
        }
        invalidate();
    }

    public void setRateValue(int rateValue) {
        this.rateValue = rateValue;
        refresRate();
    }

    private void setStatusStars(ImageView star, boolean enable) {
        if (enable) {
            star.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccentDark));
        } else {
            star.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorDisable));
        }
    }

}