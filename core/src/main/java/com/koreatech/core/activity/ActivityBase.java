package com.koreatech.core.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.koreatech.core.progressdialog.CustomProgressDialog;
import com.koreatech.core.progressdialog.IProgressDialog;

public class ActivityBase extends AppCompatActivity implements IProgressDialog {
    private FragmentManager fragmentManager;
    private CustomProgressDialog customProgressDialog;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    @Override
    public void showProgressDialog(@Nullable String message) {
        if (customProgressDialog == null) {
            customProgressDialog = new CustomProgressDialog(context, getSupportFragmentManager(), message);
            customProgressDialog.execute();
        }
    }

    @Override
    public void showProgressDialog(@StringRes int resId) {
        if (customProgressDialog == null) {
            customProgressDialog = new CustomProgressDialog(context, getSupportFragmentManager(), context.getResources().getString(resId));
            customProgressDialog.execute();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (customProgressDialog != null) {
            customProgressDialog.cancel(false);
            customProgressDialog = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (customProgressDialog != null) {
            customProgressDialog.cancel(false);
            customProgressDialog = null;
        }
    }
}
