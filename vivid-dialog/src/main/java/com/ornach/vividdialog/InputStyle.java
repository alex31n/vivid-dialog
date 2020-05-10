package com.ornach.vividdialog;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.ColorInt;

public class InputStyle {
    protected  @ColorInt Integer backgroundColor;
    protected Integer borderWidth;
    protected @ColorInt Integer borderColor;
    protected @ColorInt Integer textColor;
    protected Float textSize;
    protected @ColorInt Integer hintColor;
    protected Float radius;


    protected InputStyle getDefaultInputStyle(Context context){
        return new InputStyle()
                .setBackgroundColor(Color.TRANSPARENT)
                .setBorderColor(Utils.getColorRes(context, R.color.colorPrimary))
                .setBorderWidth(1);
    }

    public InputStyle setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public InputStyle setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public InputStyle setBorderColor(@ColorInt int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public InputStyle setTextColor(@ColorInt int textColor) {
        this.textColor = textColor;
        return this;
    }

    public InputStyle setTextSize(float textSize) {
        this.textSize = textSize;
        return this;
    }

    public InputStyle setHintColor(@ColorInt int hintColor) {
        this.hintColor = hintColor;
        return this;
    }

    public InputStyle setRadius(float radius) {
        this.radius = radius;
        return this;
    }
}
