package com.ornach.vividdialog;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

public class BaseBuilder<T extends BaseBuilder> {

    protected Context context;
    T childBuilder;

    protected boolean isCancelable =true;
    protected boolean isAutoDismissible=true;

    protected DialogInterface.OnCancelListener onCancelListener;
    protected DialogInterface.OnDismissListener onDismissListener;
    protected DialogInterface.OnShowListener onShowListener;

    protected BaseBuilder(Context context) {
        this.context = context;

    }

    protected void setChildBuilder( T childBuilder){
        this.childBuilder = childBuilder;
    }

    protected Context getContext() {
        return context;
    }

    public T setCancelable(boolean cancelable) {
        isCancelable = cancelable;
        return childBuilder;
    }

    public T setAutoDismissible(boolean dismissible) {
        isAutoDismissible = dismissible;
        return childBuilder;
    }



    public T setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        return childBuilder;
    }

    public T setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return childBuilder;
    }

    public T setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        this.onShowListener = onShowListener;
        return childBuilder;
    }
}
