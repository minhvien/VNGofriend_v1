package com.gofriend.vienngan.vngofriend.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by tmvien on 12/9/15.
 */
public class ProgressDialogFragment extends DialogFragment {

    private String mTitle;

    private String mMessage;

    private OnDismissListener mOnDismissListener;
    private OnCancelListener mOnCancelListener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        if (mTitle != null) {
            dialog.setTitle(mTitle);
        }
        if (mMessage != null) {
            dialog.setMessage(mMessage);
        }
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss(dialog);
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (mOnCancelListener != null) {
            mOnCancelListener.onCancel(dialog);
        }
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public void setOnDismissListener(OnDismissListener l) {
        mOnDismissListener = l;
    }

    public void setOnCancelListener(OnCancelListener l) {
        mOnCancelListener = l;
    }

}