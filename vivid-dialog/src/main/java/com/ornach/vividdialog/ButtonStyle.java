package com.ornach.vividdialog;

import androidx.annotation.ColorInt;

public class ButtonStyle {
    protected @ColorInt Integer textColor;
    protected @ColorInt Integer borderColor;
    protected @ColorInt Integer backgroundColor;

    public ButtonStyle setTextColor(@ColorInt int textColor) {
        this.textColor = textColor;
        return this;
    }

    public ButtonStyle setBorderColor(@ColorInt int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public ButtonStyle setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }
}
