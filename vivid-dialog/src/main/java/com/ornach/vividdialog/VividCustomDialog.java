package com.ornach.vividdialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.ornach.nobobutton.NoboButton;


public class VividCustomDialog extends BaseDialog {

    private Builder builder;

    private View layoutHeader;
    private ImageView imgIcon;
    private TextView textHeader;

    private LinearLayout layoutBody;
    private TextView textTitle;
    private TextView textMessage;

    private View layoutFooter;
    private NoboButton positiveButton;
    private NoboButton negativeButton;

    private View mView;


    private VividCustomDialog(Builder builder) {
        super(builder);
        this.builder = builder;
        setView(R.layout.vivid_custom_dialog);
        createDialog();

        initView();
        updateViews();
    }

    private void initView() {
        layoutHeader = super.getView().findViewById(R.id.layout_header);
        imgIcon = super.getView().findViewById(R.id.img_icon);
        textHeader = super.getView().findViewById(R.id.text_header_text);

        layoutBody = super.getView().findViewById(R.id.layout_body);

        layoutFooter = super.getView().findViewById(R.id.layout_footer);
        positiveButton = super.getView().findViewById(R.id.button_positive);
        negativeButton = super.getView().findViewById(R.id.button_negative);
    }

    private void updateViews() {

        updateHeader();
        updateBody();
        updateFooter();

    }

    private void updateHeader() {
        if (!builder.isHeaderEnabled || (TextUtils.isEmpty(builder.headerText) && builder.icon == null)) {

            layoutHeader.setVisibility(View.GONE);

        } else {

            // header text
            if (TextUtils.isEmpty(builder.headerText)) {
                textHeader.setVisibility(View.GONE);
            } else {
                textHeader.setText(builder.headerText);
                textHeader.setTextColor(builder.headerTextColor);
            }

            // header icon
            if (builder.icon == null) {
                imgIcon.setVisibility(View.GONE);
            } else {
                imgIcon.setImageResource(builder.icon);
                // change header icon color
                Drawable wrappedDrawable = DrawableCompat.wrap(imgIcon.getDrawable());
                DrawableCompat.setTint(wrappedDrawable, builder.iconColor);
            }

            if (builder.headerBackgroundColor != null)
                layoutHeader.setBackgroundColor(builder.headerBackgroundColor);
        }
    }

    private void updateBody() {

        if (builder.backgroundColor != null) {
            layoutBody.setBackgroundColor(builder.backgroundColor);
        }

        if (builder.view != null) {
            this.mView = builder.view;

        } else if (builder.layoutId != null) {
            this.mView = LayoutInflater.from(builder.getContext()).inflate(builder.layoutId, null, false);
        } else {
            throw new IllegalArgumentException("View must not be null.");
        }

        layoutBody.addView(this.mView);

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
                    builder.positiveButtonClickListener.onClick(VividCustomDialog.this, BUTTON_POSITIVE);
                }

                autoDismiss();
            }
        });


        // set negative button listener
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (builder.negativeButtonClickListener != null) {
                    builder.negativeButtonClickListener.onClick(VividCustomDialog.this, BUTTON_NEGATIVE);
                }
                autoDismiss();
            }
        });
    }


    public void show() {
        super.show();
    }

    public View getView() {
        return this.mView;
    }


    public static class Builder extends BaseBuilder<Builder> {


        protected Boolean isHeaderEnabled = true;
        protected @ColorInt
        Integer headerBackgroundColor;
        protected @ColorInt
        Integer backgroundColor;
        protected @ColorInt
        Integer headerTextColor;
        protected @ColorInt
        Integer iconColor;

        protected String headerText;
        protected @DrawableRes
        Integer icon;

        protected String positiveButtonText;
        protected String negativeButtonText;

        protected OnClickListener positiveButtonClickListener;
        protected OnClickListener negativeButtonClickListener;

        protected ButtonStyle negativeButtonStyle;
        protected ButtonStyle positiveButtonStyle;

        protected View view;
        protected @LayoutRes
        Integer layoutId;

        public Builder(Context context) {
            super(context);

            defaultBuilder();

            setChildBuilder(this);
        }

        private void defaultBuilder() {
            this.isHeaderEnabled = true;
            this.headerText = "";
            this.headerBackgroundColor = Utils.getColorRes(context, R.color.colorPrimary);
            this.backgroundColor = Color.WHITE;
            this.headerTextColor = Color.WHITE;
            this.iconColor = Color.WHITE;

            this.negativeButtonStyle = new ButtonStyle()
                    .setBackgroundColor(Color.TRANSPARENT)
                    .setBorderColor(Utils.getColorRes(context, R.color.colorPrimary))
                    .setTextColor(Utils.getColorRes(context, R.color.textColorDark));

            this.positiveButtonStyle = new ButtonStyle()
                    .setBackgroundColor(Utils.getColorRes(context, R.color.colorPrimary))
                    .setBorderColor(Utils.getColorRes(context, R.color.colorPrimary))
                    .setTextColor(Color.WHITE);

        }

        public VividCustomDialog build() {

            if (view == null && layoutId == null) {
                throw new IllegalArgumentException("View must not be null.");
            }

            return new VividCustomDialog(this);
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

        public Builder setNegativeButtonStyle(ButtonStyle negativeButtonStyle) {
            this.negativeButtonStyle = negativeButtonStyle;
            return this;
        }

        public Builder setPositiveButtonStyle(ButtonStyle positiveButtonStyle) {
            this.positiveButtonStyle = positiveButtonStyle;
            return this;
        }

        public Builder setView(View view) {
            this.view = view;
            return this;
        }

        public Builder setView(@LayoutRes int layoutId) {
            this.layoutId = layoutId;
            return this;
        }
    }
}
