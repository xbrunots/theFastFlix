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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devbruno.fastshop.R;
import com.devbruno.fastshop.infraestruture.Constants;

/**
 * Created by bsilvabr on 10/02/2018.
 */


public class CustomPayDialog extends Dialog implements View.OnClickListener {
    private Button buttonPay;
    private RelativeLayout relativeBody;
    private LinearLayout linearLayout;
    private Activity activity;
    private TextView userName;

    public CustomPayDialog(Activity activity) {
        super(activity);
        // TODO Auto-generated constructor stub
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pay_dialog);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        init();
        setListeners();
        animAfterViews();
    }

    private void init() {
        buttonPay = (Button) findViewById(R.id.button2);
        relativeBody = (RelativeLayout) findViewById(R.id.relative_body);
        linearLayout = (LinearLayout) findViewById(R.id.linearbody);
        userName = (TextView) findViewById(R.id.textViewUserName);
    }

    private void setListeners() {
        buttonPay.setOnClickListener(this);
        relativeBody.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
    }

    private void animAfterViews() {
        userName.setText("Guest");
        buttonPay.setText(Constants.BUTTON_BUY_TITLE);
        relativeBody.startAnimation(AnimationUtils.loadAnimation(relativeBody.getContext(), R.anim.chat_move_down));
    }

    private void close() {
        dismiss();
    }

    private void animateClose() {
        close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
            case R.id.relative_body:
            case R.id.linearbody:
                animateClose();
                break;
            default:
                break;
        }
        close();
    }

}