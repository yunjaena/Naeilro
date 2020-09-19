package com.koreatech.core.progressdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.koreatech.core.R;


public class LottieDialogFragment extends DialogFragment {

    String jsonFileName;
    int loopCount;
    LottieProgressDialog progressDialog;

    public LottieDialogFragment newInstance(String jsonFileName) {
        LottieDialogFragment fragment = new LottieDialogFragment();

        Bundle args = new Bundle();
        args.putString("jsonFileName", jsonFileName);
        args.putBoolean("isLoopEnabled", true);
        fragment.setArguments(args);

        return fragment;
    }

    public LottieDialogFragment newInstance(String jsonFileName, int loopCount) {

        LottieDialogFragment fragment = new LottieDialogFragment();

        Bundle args = new Bundle();
        args.putString("jsonFileName", jsonFileName);
        args.putInt("loopCount", loopCount);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        jsonFileName = getArguments().getString("jsonFileName");
        loopCount = getArguments().getInt("loopCount");

        progressDialog = new LottieProgressDialog(getActivity());
        progressDialog.setJsonFileName(jsonFileName);
        progressDialog.setLoop(loopCount);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return progressDialog;
    }

    private class LottieProgressDialog extends AlertDialog {
        private LottieAnimationView lavProgress;
        private String jsonFileName;
        private int loop = LottieDrawable.INFINITE;

        private LottieProgressDialog(Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.lottie_progress_dialog);

            Window currentWindow = this.getWindow();

            if (currentWindow != null) {
                currentWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            lavProgress = findViewById(R.id.lavProgress);
            lavProgress.setAnimation(jsonFileName);
            lavProgress.setRepeatCount(loop);
            lavProgress.playAnimation();
        }

        @Override
        public void dismiss() {
            if (lavProgress != null) {
                lavProgress.cancelAnimation();
            }
            super.dismiss();
        }

        private String getJsonFileName() {
            return jsonFileName;
        }

        private void setJsonFileName(String jsonFileName) {
            this.jsonFileName = jsonFileName;
        }

        public int getLoop() {
            return loop;
        }

        public void setLoop(int loop) {
            this.loop = loop;
        }
    }

}