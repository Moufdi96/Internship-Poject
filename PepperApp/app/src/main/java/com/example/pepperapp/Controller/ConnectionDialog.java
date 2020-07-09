package com.example.pepperapp.Controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.pepperapp.R;

public class ConnectionDialog extends AppCompatDialogFragment {
    private String mTitle;
    private String mButtonName;
    private String mMessage;
    private DialogInterface.OnClickListener mListner;

    public ConnectionDialog(String title, String buttonName, String message, DialogInterface.OnClickListener listner) {
        this.mTitle = title;
        this.mButtonName = buttonName;
        this.mMessage = message;
        this.mListner = listner;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(this.mTitle)
                .setMessage(mMessage)
                .setPositiveButton(mButtonName,mListner);
        return builder.create();
    }
}
