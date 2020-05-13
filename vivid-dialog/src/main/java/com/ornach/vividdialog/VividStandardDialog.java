package com.ornach.vividdialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;

import com.ornach.nobobutton.NoboButton;


public class VividStandardDialog extends BaseDialog {
    public enum ThemeType {
        NORMAL, DARK, SUCCESS, WARNING, INFORMATION
    }

    private Builder builder;

    private View layoutHeader;
    private ImageView imgIcon;
    private TextView textHeader;

    private View layoutBody;
    private TextView textTitle;
    private TextView textMessage;

    private View layoutFooter;
    private NoboButton positiveButton;
    private NoboButton negativeButton;


    private VividStandardDialog(Builder builder) {
        super(builder);
        this.builder = builder;
        setView(R.layout.fragment_vivid_standard_dialog);
        createDialog();

        updateBuilder();
        initView();
        updateViews();
    }

    private void initView() {
        layoutHeader = getView().findViewById(R.id.layout_header);
        imgIcon = getView().findViewById(R.id.img_icon);
        textHeader = getView().findViewById(R.id.text_header_text);

        layoutBody = getView().findViewById(R.id.layout_body);
        textTitle = getView().findViewById(R.id.text_title);
        textMessage = getView().findViewById(R.id.text_message);

        layoutFooter = getView().findViewById(R.id.layout_footer);
        positiveButton = getView().findViewById(R.id.button_positive);
        negativeButton = getView().findViewById(R.id.button_negative);
    }

    private void updateViews() {

        updateHeader();
        updateBody();
        updateFooter();

    }

    private void updateHeader(){
        if (!builder.isHeaderEnabled ||(TextUtils.isEmpty(builder.headerText) && builder.icon==null)){

            layoutHeader.setVisibility(View.GONE);

        }else {

            // header text
            if (TextUtils.isEmpty(builder.headerText)){
                textHeader.setVisibility(View.GONE);
            }else {
                textHeader.setText(builder.headerText);
                textHeader.setTextColor(builder.headerTextColor);
            }

            // header icon
            if (builder.icon==null){
                imgIcon.setVisibility(View.GONE);
            }else {
                imgIcon.setImageResource(builder.icon);
                // change header icon color
                Drawable wrappedDrawable = DrawableCompat.wrap(imgIcon.getDrawable());
                DrawableCompat.setTint(wrappedDrawable, builder.iconColor);
            }

            if (builder.headerBackgroundColor != null)
                layoutHeader.setBackgroundColor(builder.headerBackgroundColor);
        }
    }

    private void updateBody(){

        if (builder.backgroundColor!=null){
            layoutBody.setBackgroundColor(builder.backgroundColor);
        }

        // body title
        if (TextUtils.isEmpty(builder.title)){
            textTitle.setVisibility(View.GONE);
        }else {
            textTitle.setText(builder.title);
            textTitle.setTextColor(builder.textColor);
        }

        // body message
        if (TextUtils.isEmpty(builder.message)){
            textMessage.setVisibility(View.GONE);
        }else {
            textMessage.setText(builder.message);
            textMessage.setTextColor(builder.textColor);
        }

    }

    private void updateFooter() {

        layoutFooter.setVisibility(
                (!TextUtils.isEmpty(builder.negativeButtonText) ||
                        !TextUtils.isEmpty(builder.positiveButtonText)) ?
                        View.VISIBLE :
                        View.GONE

        );


        if (!TextUtils.isEmpty(builder.negativeButtonText)) {
            negativeButton.setVisibility(View.VISIBLE);
            negativeButton.setText(builder.negativeButtonText);
            Utils.updateButtonStyle(negativeButton, builder.negativeButtonStyle);
        } else {
            negativeButton.setVisibility(View.INVISIBLE);
        }

        if (!TextUtils.isEmpty(builder.positiveButtonText)) {
            positiveButton.setVisibility(View.VISIBLE);
            positiveButton.setText(builder.positiveButtonText);
            Utils.updateButtonStyle(positiveButton, builder.positiveButtonStyle);
        } else {
            positiveButton.setVisibility(View.INVISIBLE);
        }

        // set positive button listener
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (builder.positiveButtonClickListener != null) {
                    builder.positiveButtonClickListener.onClick(VividStandardDialog.this, BUTTON_POSITIVE);
                }
                dismiss();
            }
        });


        // set negative button listener
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (builder.negativeButtonClickListener != null) {
                    builder.negativeButtonClickListener.onClick(VividStandardDialog.this, BUTTON_NEGATIVE);
                }
                dismiss();
            }
        });
    }

    private void updateBuilder() {
        switch (builder.themeType) {
            case SUCCESS:
                updateBuilderForSuccess();
                break;
            case WARNING:
                updateBuilderForWarning();
                break;
            case INFORMATION:
                updateBuilderForInformation();
                break;
            case DARK:
                updateBuilderForDark();
                break;
            case NORMAL:
            default:
                updateBuilderForNormal();
                break;
        }

    }

    private void updateBuilderForSuccess() {

        int color = getColorRes(R.color.colorSuccess);

        if (builder.headerBackgroundColor == null) {
            builder.headerBackgroundColor = color;
        }

        if (builder.iconColor == null) {
            builder.iconColor = Color.WHITE;
        }

        if (builder.headerTextColor == null) {
            builder.headerTextColor = Color.WHITE;
        }

        if (builder.backgroundColor == null) {
            builder.backgroundColor = ColorUtils.setAlphaComponent(
                    color,
                    30
            );
        }

        if (builder.textColor == null) {
            builder.textColor = getColorRes(R.color.textColorDark);
        }

        if (builder.negativeButtonStyle ==null){
            builder.setNegativeButtonStyle(
                    new ButtonStyle()
                    .setTextColor(Color.BLACK)
                    .setBorderColor(color)
                    .setBackgroundColor(Color.TRANSPARENT)
            );
        }

        if (builder.positiveButtonStyle ==null){
            builder.setPositiveButtonStyle(
                    new ButtonStyle()
                    .setTextColor(Color.WHITE)
                    .setBorderColor(color)
                    .setBackgroundColor(color)
            );
        }


    }

    private void updateBuilderForWarning() {

        int color = getColorRes(R.color.colorWarning);

        if (builder.headerBackgroundColor == null) {
            builder.headerBackgroundColor = color;
        }

        if (builder.iconColor == null) {
            builder.iconColor = Color.WHITE;
        }

        if (builder.headerTextColor == null) {
            builder.headerTextColor = Color.WHITE;
        }

        if (builder.backgroundColor == null) {
            builder.backgroundColor = ColorUtils.setAlphaComponent(
                    color,
                    30
            );
        }

        if (builder.textColor == null) {
            builder.textColor = getColorRes(R.color.textColorDark);
        }

        if (builder.negativeButtonStyle ==null){
            builder.setNegativeButtonStyle(
                    new ButtonStyle()
                    .setTextColor(Color.BLACK)
                    .setBorderColor(color)
                    .setBackgroundColor(Color.TRANSPARENT)
            );
        }

        if (builder.positiveButtonStyle ==null){
            builder.setPositiveButtonStyle(
                    new ButtonStyle()
                    .setTextColor(Color.WHITE)
                    .setBorderColor(color)
                    .setBackgroundColor(color)
            );
        }


    }

    private void updateBuilderForInformation() {

        int color = getColorRes(R.color.colorInformation);

        if (builder.headerBackgroundColor == null) {
            builder.headerBackgroundColor = color;
        }

        if (builder.iconColor == null) {
            builder.iconColor = Color.WHITE;
        }

        if (builder.headerTextColor == null) {
            builder.headerTextColor = Color.WHITE;
        }

        if (builder.backgroundColor == null) {
            builder.backgroundColor = ColorUtils.setAlphaComponent(
                    color,
                    30
            );
        }

        if (builder.textColor == null) {
            builder.textColor = getColorRes(R.color.textColorDark);
        }

        if (builder.negativeButtonStyle ==null){
            builder.setNegativeButtonStyle(
                    new ButtonStyle()
                            .setTextColor(Color.BLACK)
                            .setBorderColor(color)
                            .setBackgroundColor(Color.TRANSPARENT)
            );
        }

        if (builder.positiveButtonStyle ==null){
            builder.setPositiveButtonStyle(
                    new ButtonStyle()
                            .setTextColor(Color.WHITE)
                            .setBorderColor(color)
                            .setBackgroundColor(color)
            );
        }


    }

    private void updateBuilderForDark() {

        int color = getColorRes(R.color.colorDark);

        if (builder.headerBackgroundColor == null) {
            builder.headerBackgroundColor = color;
        }

        if (builder.iconColor == null) {
            builder.iconColor = Color.WHITE;
        }

        if (builder.headerTextColor == null) {
            builder.headerTextColor = Color.WHITE;
        }

        if (builder.backgroundColor == null) {
            builder.backgroundColor = ColorUtils.setAlphaComponent(
                    color,
                    230
            );
        }

        if (builder.textColor == null) {
            builder.textColor = getColorRes(R.color.textColorLight);
        }

        if (builder.negativeButtonStyle ==null){
            builder.setNegativeButtonStyle(
                    new ButtonStyle()
                            .setTextColor(Color.WHITE)
                            .setBorderColor(Color.WHITE)
                            .setBackgroundColor(color)
            );
        }

        if (builder.positiveButtonStyle ==null){
            builder.setPositiveButtonStyle(
                    new ButtonStyle()
                            .setTextColor(Color.WHITE)
                            .setBorderColor(Color.WHITE)
                            .setBackgroundColor(color)
            );
        }


    }

    private void updateBuilderForNormal() {

        if (builder.headerBackgroundColor == null) {
            builder.headerBackgroundColor = getColorRes(R.color.colorPrimary);
        }

        if (builder.iconColor == null) {
            builder.iconColor = Color.WHITE;
        }

        if (builder.headerTextColor == null) {
            builder.headerTextColor = Color.WHITE;
        }

        if (builder.backgroundColor == null) {
            builder.backgroundColor = Color.WHITE;
        }

        if (builder.textColor == null) {
            builder.textColor = getColorRes(R.color.textColorDark);
        }

        if (builder.negativeButtonStyle ==null){
            builder.setNegativeButtonStyle(
                    new ButtonStyle()
                            .setTextColor(getColorRes(R.color.textColorDark))
                            .setBorderColor(getColorRes(R.color.colorPrimary))
                            .setBackgroundColor(Color.TRANSPARENT)
            );
        }

        if (builder.positiveButtonStyle ==null){
            builder.setPositiveButtonStyle(
                    new ButtonStyle()
                            .setTextColor(Color.WHITE)
                            .setBorderColor(getColorRes(R.color.colorPrimary))
                            .setBackgroundColor(getColorRes(R.color.colorPrimary))
            );
        }


    }


    private int getColorRes(@ColorRes int colorRes) {
        return ContextCompat.getColor(builder.getContext(), colorRes);
    }



    /*private void updateBuilderAsDefaultValue() {
        builder.iconColor = Color.WHITE;
        builder.headerTextColor = Color.WHITE;

        if (builder.headerBackgroundColor == null) {
            builder.headerBackgroundColor = ContextCompat.getColor(
                    context,
                    builder.isDarkTheme ? R.color.headerBackgroundColorDark : R.color.colorPrimary);
        }

        if (TextUtils.isEmpty(builder.headerText)) {
            builder.headerText = context.getString(R.string.app_name);
        }


        if (builder.backgroundColor == null) {

            builder.backgroundColor = ContextCompat.getColor(
                    context,
                    builder.isDarkTheme ? R.color.backgroundColorDark : R.color.backgroundColorLight
            );
        }

        if (builder.textColor == null) {
            builder.textColor = ContextCompat.getColor(
                    context,
                    builder.isDarkTheme ? R.color.textColorLight : R.color.textColorDark
            );
        }


    }*/


    public void show() {
        super.show();
    }


    public static class Builder extends BaseBuilder<Builder> {

        protected ThemeType themeType = ThemeType.NORMAL;

        protected Boolean isHeaderEnabled=true;
        protected @ColorInt Integer headerBackgroundColor;
        protected @ColorInt Integer backgroundColor;
        protected @ColorInt Integer headerTextColor;
        protected @ColorInt Integer iconColor;
        protected @ColorInt Integer textColor;

        protected String headerText;
        protected @DrawableRes
        Integer icon;

        protected String positiveButtonText;
        protected String negativeButtonText;

        protected OnClickListener positiveButtonClickListener;
        protected OnClickListener negativeButtonClickListener;

        protected String title;
        protected String message;

        protected ButtonStyle negativeButtonStyle;
        protected ButtonStyle positiveButtonStyle;

        public Builder(Context context) {
            super(context);

            defaultBuilder();

            setChildBuilder(this);
        }

        private void defaultBuilder() {

        }

        public VividStandardDialog build() {
            return new VividStandardDialog(this);
        }

        public Builder setHeaderEnabled(boolean headerEnabled) {
            isHeaderEnabled = headerEnabled;
            return this;
        }

        public Builder setHeaderBackgroundColor(@ColorInt int color) {
            this.headerBackgroundColor = color;
            return this;
        }

        public Builder setHeaderBackgroundColorRes(@ColorRes int colorRes) {
            this.headerBackgroundColor = ContextCompat.getColor(getContext(), colorRes);
            return this;
        }

        public Builder setBackgroundColor(@ColorInt int color) {
            this.backgroundColor = color;
            return this;
        }

        public Builder setBackgroundColorRes(@ColorRes int colorRes) {
            this.backgroundColor = ContextCompat.getColor(getContext(), colorRes);
            return this;
        }

        public Builder setHeaderTextColor(@ColorInt int color) {
            this.headerTextColor = color;
            return this;
        }

        public Builder setHeaderTextColorRes(@ColorRes int colorRes) {
            this.headerTextColor = ContextCompat.getColor(getContext(), colorRes);
            return this;
        }

        public Builder setIconColorRes(@ColorRes int iconColorRes) {
            this.iconColor = ContextCompat.getColor(getContext(), iconColorRes);
            return this;
        }

        public Builder setIconColor(@ColorInt int iconColor) {
            this.iconColor = iconColor;
            return this;
        }

        public Builder setHeaderText(String headerText) {
            this.headerText = headerText;
            return this;
        }

        public Builder setIcon(int icon) {
            this.icon = icon;
            return this;
        }

        public Builder setTextColor(@ColorInt int color) {
            this.textColor = color;
            return this;
        }

        public Builder setTextColorRes(@ColorRes int color) {
            this.textColor = ContextCompat.getColor(getContext(), color);
            return this;
        }

        public Builder setPositiveButton(String text, @Nullable OnClickListener listener) {
            this.positiveButtonText = text;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String text, @Nullable OnClickListener listener) {
            this.negativeButtonText = text;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setThemeType(ThemeType themeType) {
            this.themeType = themeType;
            return this;
        }

        public Builder setNegativeButtonStyle(ButtonStyle negativeButtonStyle) {
            this.negativeButtonStyle = negativeButtonStyle;
            return this;
        }

        public Builder setPositiveButtonStyle(ButtonStyle positiveButtonStyle) {
            this.positiveButtonStyle = positiveButtonStyle;
            return this;
        }
    }
}
