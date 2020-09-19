package com.koreatech.core.progressdialog;

import android.content.Context;
import android.os.AsyncTask;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;


public class CustomProgressDialog extends AsyncTask<Void, Void, Void> {
    private final String TAG = "CustomProgressDialog";

    private DialogFragment progressDialog;
    private String message;
    private FragmentManager fragmentManager;

    public CustomProgressDialog(Context context, FragmentManager fragmentManager, String msg) {
        this.message = msg;
        this.fragmentManager = fragmentManager;
        progressDialog = new LottieDialogFragment().newInstance("lottie_running_train_with_light.json");
        progressDialog.setCancelable(false);
    }

    @Override
    protected void onPreExecute() {
        // progressDialog.setMessage(message);
        progressDialog.show(fragmentManager, "lottieDialog");
    }

    @Override
    protected Void doInBackground(Void... params) {
        while (true) {
            if (isCancelled()) {
                break;
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
        if (progressDialog != null)
            progressDialog.dismiss();
        progressDialog = null;
    }
}
