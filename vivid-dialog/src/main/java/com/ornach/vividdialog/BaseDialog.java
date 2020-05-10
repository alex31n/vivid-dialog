package com.ornach.vividdialog;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;


public class BaseDialog implements DialogInterface {

    protected String TAG = this.getClass().getSimpleName();

    protected BaseBuilder builder;
    protected Context context;

    private View mView;
    private AlertDialog mDialog;
    private int mViewLayoutResId=0;

    public BaseDialog(BaseBuilder builder) {
        this.context =builder.getContext();
        this.builder =builder;

    }



    public View getView() {
        return mView;
    }

    /**
     * Set the view resource to display in the dialog.
     */
    protected void setView(int layoutResId) {
        this.mView = LayoutInflater.from(context).inflate(layoutResId, null, false);
        this.mViewLayoutResId= layoutResId;
    }

    /**
     * Set the view to display in the dialog.
     */
    protected void setView(View view) {
        this.mView = view;
        mViewLayoutResId = 0;
    }


    protected AlertDialog createDialog(){


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        // set view if java view found
        // and otherwise if resource view found then add resource layout
        if (mView !=null){
            dialogBuilder.setView(mView);
        }else if (mViewLayoutResId!=0){
            dialogBuilder.setView(mViewLayoutResId);
        }

        // set cancelable
        dialogBuilder.setCancelable(builder.isCancelable);

        // set dismiss listener
        if (builder.onDismissListener!=null) {
            dialogBuilder.setOnDismissListener(new android.content.DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(android.content.DialogInterface dialog) {
                    builder.onDismissListener.onDismiss(BaseDialog.this);
                }
            });
        }

        // set cancel listener
        if (builder.onCancelListener!=null){
            dialogBuilder.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(android.content.DialogInterface dialog) {
                    builder.onCancelListener.onCancel(BaseDialog.this);
                }
            });
        }

        // generate alert dialog
        mDialog = dialogBuilder.create();


        // set show listener
        if (builder.onShowListener!=null) {
            mDialog.setOnShowListener(new android.content.DialogInterface.OnShowListener() {
                @Override
                public void onShow(android.content.DialogInterface dialog) {
                    builder.onShowListener.onShow(BaseDialog.this);
                }
            });
        }


        return mDialog;
    }

    @Override
    public void cancel() {
        if (mDialog == null) {
            Log.e(TAG, "show: mDialog is null");
            return;
        }

        mDialog.cancel();
    }

    @Override
    public void dismiss() {
        if (mDialog == null) {
            Log.e(TAG, "show: mDialog is null");
            return;
        }

        mDialog.dismiss();
    }

    @Override
    public void show() {
        if (mDialog == null) {
            Log.e(TAG, "show: mDialog is null");
            return;
        }

        mDialog.show();
    }
}
