package com.devbruno.fastshop.presentation.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Field;

import com.devbruno.fastshop.infraestruture.Constants;

public class CustomToolbar extends Toolbar {

    //Accessibility Toolbar Fields
    private static final String TITLE_FIELD_NAME = "mTitleTextView";
    private static final String NAV_BUTTON_FIELD_NAME = "mNavButtonView";

    private Field mTitleTextViewField;
    private Field mNavButtonViewField;

    private TextView mTitleTextView;
    private ImageButton mNavButton;

    private String mTitleContentDescription;

    public CustomToolbar(Context context) {
        super(context);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initialize();
    }

    private void initialize() {
        setToolbarToAccessibilityFields();
        setToolbarToAccessibilityViews();
        changeTitleContentDescription();
    }

    private void setToolbarToAccessibilityFields() {
        Class<?> toolbarClass = Toolbar.class;
        try {
            mTitleTextViewField = toolbarClass.getDeclaredField(TITLE_FIELD_NAME);
            mNavButtonViewField = toolbarClass.getDeclaredField(NAV_BUTTON_FIELD_NAME);

            mTitleTextViewField.setAccessible(true);
            mNavButtonViewField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            Log.d(Constants.ERRO_TOOLBAR, CustomToolbar.class.getSimpleName(), e);
        }
    }

    private void setToolbarToAccessibilityViews() {
        try {
            mTitleTextView = (TextView) mTitleTextViewField.get(this);
            mNavButton = (ImageButton) mNavButtonViewField.get(this);
        } catch (IllegalAccessException e) {
            Log.d(Constants.ERRO_TOOLBAR, CustomToolbar.class.getSimpleName(), e);
        }
    }

    private void changeTitleContentDescription() {
        if (!TextUtils.isEmpty(mTitleContentDescription)) {
            mTitleTextView.setContentDescription(mTitleContentDescription);
        }
    }

    public void setTitleContentDescription(String contentDescription) {
        mTitleContentDescription = contentDescription;
    }


    public void setIcon(int icon) {
        mNavButton.setImageResource(icon);
    }

}
