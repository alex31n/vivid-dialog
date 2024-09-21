package com.github.alex31n.vivid_dialog;

import android.content.Context;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

import com.github.nobobutton.NoboButton;


public class Utils {

    protected static int getColorRes(Context context, @ColorRes int colorRes) {
        return ContextCompat.getColor(context, colorRes);
    }

    protected static void updateButtonStyle(NoboButton button, ButtonStyle style){
        if (style==null){
            return;
        }
        if (style.backgroundColor!=null){
            button.setBackgroundColor(style.backgroundColor);
        }

        if (style.borderColor!=null){
            button.setBorderColor(style.borderColor);
        }

        if (style.textColor!=null){
            button.setTextColor(style.textColor);
        }
    }
}
