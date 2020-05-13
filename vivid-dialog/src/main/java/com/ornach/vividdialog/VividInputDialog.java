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
import androidx.core.graphics.drawable.DrawableCompat;

import com.ornach.nobobutton.NoboButton;
import com.ornach.richtext.RichEditText;

public class VividInputDialog extends BaseDialog {
    Builder builder;

    private View layoutHeader;
    private ImageView imgIcon;
    private TextView textHeader;

    private View layoutBody;
    private TextView textTitle;
    private TextView textMessage;
    private RichEditText inputText;

    private View layoutFooter;
    private NoboButton positiveButton;
    private NoboButton negativeButton;


    private VividInputDialog(Builder builder) {
        super(builder);
        this.builder = builder;
        setView(R.layout.vivid_input_dialog);
        createDialog();
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
        inputText = getView().findViewById(R.id.inputEditText);

        layoutFooter = getView().findViewById(R.id.layout_footer);
        positiveButton = getView().findViewById(R.id.button_positive);
        negativeButton = getView().findViewById(R.id.button_negative);
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

        if (builder.backgroundColor!=null){
            layoutBody.setBackgroundColor(builder.backgroundColor);
        }

        // body title
        if (TextUtils.isEmpty(builder.title)) {
            textTitle.setVisibility(View.GONE);
        } else {
            textTitle.setText(builder.title);
            textTitle.setTextColor(builder.textColor);
        }

        // body message
        if (TextUtils.isEmpty(builder.message)) {
            textMessage.setVisibility(View.GONE);
        } else {
            textMessage.setText(builder.message);
            textMessage.setTextColor(builder.textColor);
        }


        if (!TextUtils.isEmpty(builder.inputText)) {
            inputText.setText(builder.inputText);
        }

        if (!TextUtils.isEmpty(builder.hintText)) {
            inputText.setHint(builder.hintText);
        }

        // input style
        if (builder.inputStyle != null) {
            if (builder.inputStyle.backgroundColor != null)
                inputText.setBackgroundColor(builder.inputStyle.backgroundColor);
            if (builder.inputStyle.borderColor != null)
                inputText.setBorderColor(builder.inputStyle.borderColor);
            if (builder.inputStyle.borderWidth != null)
                inputText.setBorderWidth(builder.inputStyle.borderWidth);
            if (builder.inputStyle.borderWidth != null)
                inputText.setBorderWidth(builder.inputStyle.borderWidth);
            if (builder.inputStyle.textColor != null)
                inputText.setTextColor(builder.inputStyle.textColor);
            if (builder.inputStyle.textSize != null)
                inputText.setTextSize(builder.inputStyle.textSize);
            if (builder.inputStyle.hintColor != null)
                inputText.setHintTextColor(builder.inputStyle.hintColor);
            if (builder.inputStyle.radius != null) inputText.setRadius(builder.inputStyle.radius);
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
                if (builder.submitButtonListener != null) {
                    builder.submitButtonListener.onSubmit(VividInputDialog.this, inputText.getText().toString());
                }
                autoDismiss();
            }
        });


        // set negative button listener
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (builder.negativeButtonClickListener != null) {
                    builder.negativeButtonClickListener.onClick(VividInputDialog.this, BUTTON_NEGATIVE);
                }
                autoDismiss();
            }
        });
    }


    public void show() {
        super.show();
    }

    public View getInputView(){
        return this.inputText;
    }



    public static class Builder extends BaseBuilder<Builder> {


        protected boolean isHeaderEnabled = true;
        protected @ColorInt
        Integer headerBackgroundColor;
        protected @ColorInt
        Integer backgroundColor;
        protected @ColorInt
        Integer headerTextColor;
        protected @ColorInt
        Integer iconColor;
        protected @ColorInt
        Integer textColor;

        protected String headerText;
        protected @DrawableRes
        Integer icon;

        protected String title;
        protected String message;

        protected ButtonStyle negativeButtonStyle;
        protected ButtonStyle positiveButtonStyle;

        protected String positiveButtonText;
        protected String negativeButtonText;

        protected OnSubmitListener submitButtonListener;
        protected OnClickListener negativeButtonClickListener;

        protected String hintText;
        protected String inputText;
        protected InputStyle inputStyle;

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


            this.title = "";
            this.message = "";
            this.textColor = Utils.getColorRes(context, R.color.textColorDark);

            this.inputStyle = new InputStyle()
                    .setBackgroundColor(Color.TRANSPARENT)
                    .setBorderColor(Utils.getColorRes(context, R.color.colorPrimary))
                    .setBorderWidth(1);

            this.negativeButtonStyle = new ButtonStyle()
                    .setBackgroundColor(Color.TRANSPARENT)
                    .setBorderColor(Utils.getColorRes(context, R.color.colorPrimary))
                    .setTextColor(Utils.getColorRes(context, R.color.textColorDark));

            this.positiveButtonStyle = new ButtonStyle()
                    .setBackgroundColor(Utils.getColorRes(context, R.color.colorPrimary))
                    .setBorderColor(Utils.getColorRes(context, R.color.colorPrimary))
                    .setTextColor(Color.WHITE);

        }

        public VividInputDialog build() {
            return new VividInputDialog(this);
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

        public Builder setSubmitButtonListener(String text, @Nullable OnSubmitListener listener) {
            this.positiveButtonText = text;
            this.submitButtonListener = listener;
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

        public Builder setNegativeButtonStyle(ButtonStyle negativeButtonStyle) {
            this.negativeButtonStyle = negativeButtonStyle;
            return this;
        }

        public Builder setPositiveButtonStyle(ButtonStyle positiveButtonStyle) {
            this.positiveButtonStyle = positiveButtonStyle;
            return this;
        }

        public Builder setInputStyle(InputStyle inputStyle) {
            this.inputStyle = inputStyle;
            return this;
        }

        public Builder setInputBackgroundColor(@ColorInt int color) {
            if (this.inputStyle == null) {
                this.inputStyle = new InputStyle();
            }

            this.inputStyle.setBackgroundColor(color);
            return this;
        }

        public Builder setInputBorderColor(@ColorInt int color) {
            if (this.inputStyle == null) {
                this.inputStyle = new InputStyle();
            }
            this.inputStyle.setBorderColor(color);
            return this;
        }

        public Builder setInputBorderWidth(int size) {
            if (this.inputStyle == null) {
                this.inputStyle = new InputStyle();
            }
            this.inputStyle.setBorderWidth(size);
            return this;
        }

        public Builder setInputRadius(int radius) {
            if (this.inputStyle == null) {
                this.inputStyle = new InputStyle();
            }
            this.inputStyle.setRadius(radius);
            return this;
        }

        public Builder setInputTextSize(int textSize) {
            if (this.inputStyle == null) {
                this.inputStyle = new InputStyle();
            }
            this.inputStyle.setTextSize(textSize);
            return this;
        }

        public Builder setInputTextColor(@ColorInt int color) {
            if (this.inputStyle == null) {
                this.inputStyle = new InputStyle();
            }
            this.inputStyle.setTextColor(color);
            return this;
        }

        public Builder setInputHintColor(@ColorInt int color) {
            if (this.inputStyle == null) {
                this.inputStyle = new InputStyle();
            }
            this.inputStyle.setHintColor(color);
            return this;
        }

        public Builder setInputText(String text) {
            this.inputText = text;
            return this;
        }

        public Builder setInputHintText(String text) {
            this.hintText = text;
            return this;
        }


    }
}
