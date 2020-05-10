package com.ornach.vividdialog;

public interface DialogInterface {

    /** The identifier for the positive button. */
    int BUTTON_POSITIVE = -1;

    /** The identifier for the negative button. */
    int BUTTON_NEGATIVE = -2;


    /**
     * Cancels the dialog, invoking the {@link OnCancelListener}.
     * <p>
     * The {@link OnDismissListener} may also be called if cancellation
     * dismisses the dialog.
     */
    void cancel();

    /**
     * Dismisses the dialog, invoking the {@link OnDismissListener}.
     */
    void dismiss();

    /**
     * Start the dialog and display it on screen.  The window is placed in the
     * application layer and opaque.  Note that you should not override this
     * method to do initialization when the dialog is shown.
     */
    void show();


    /**
     * Interface used to allow the creator of a dialog to run some code when the
     * dialog is canceled.
     * <p>
     * This will only be called when the dialog is canceled, if the creator
     * needs to know when it is dismissed in general, use
     * {@link OnDismissListener}.
     */
    interface OnCancelListener {
        /**
         * This method will be invoked when the dialog is canceled.
         *
         * @param dialog the dialog that was canceled will be passed into the
         *               method
         */
        void onCancel(DialogInterface dialog);
    }

    /**
     * Interface used to allow the creator of a dialog to run some code when the
     * dialog is dismissed.
     */
    interface OnDismissListener {
        /**
         * This method will be invoked when the dialog is dismissed.
         *
         * @param dialog the dialog that was dismissed will be passed into the
         *               method
         */
        void onDismiss(DialogInterface dialog);
    }

    /**
     * Interface used to allow the creator of a dialog to run some code when the
     * dialog is shown.
     */
    interface OnShowListener {
        /**
         * This method will be invoked when the dialog is shown.
         *
         * @param dialog the dialog that was shown will be passed into the
         *               method
         */
        void onShow(DialogInterface dialog);
    }

    /**
     * Interface used to allow the creator of a dialog to run some code when an
     * item on the dialog is clicked.
     */
    interface OnClickListener {
        /**
         * This method will be invoked when a button in the dialog is clicked.
         *
         * @param dialog the dialog that received the click
         * @param which the button that was clicked (ex.
         *              {@link DialogInterface#BUTTON_POSITIVE})
         */
        void onClick(DialogInterface dialog, int which);
    }

    /**
     * Interface used to submit a input dialog to return text when
     * user click submit button
     */
    interface OnSubmitListener {
        /**
         * This method will be invoked when a button in the dialog is clicked.
         *
         * @param dialog the dialog that received the click
         * @param text return user inputted text
         */
        void onSubmit(DialogInterface dialog, String text);
    }


}
