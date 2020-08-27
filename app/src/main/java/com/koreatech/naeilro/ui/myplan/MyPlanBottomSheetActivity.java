package com.koreatech.naeilro.ui.myplan;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.koreatech.core.activity.ActivityBase;
import com.koreatech.naeilro.R;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;

public class MyPlanBottomSheetActivity extends ActivityBase {
    public static final String TAG = "MyPlanBottomSheetActivity";
    public final double BOTTOM_SHEET_SIZE_SHOW_PERCENT = 0.4;
    public final double BOTTOM_SHEET_SIZE_EXPENDED_PERCENT = 0.95;
    private LinearLayout myPlanSaveCardView;
    private BottomSheetBehavior behavior;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan_bottom_sheet);
        init();
    }

    private void init() {
        context = this;
        myPlanSaveCardView = findViewById(R.id.my_plan_save_card_view);
        setBottomSheet();
    }

    private void setBottomSheet() {
        int maxHeight = (int) (getWindowHeight() * BOTTOM_SHEET_SIZE_SHOW_PERCENT);
        behavior = BottomSheetBehavior.from(myPlanSaveCardView);
        behavior.setPeekHeight(maxHeight);
        ViewGroup.LayoutParams layoutParams = myPlanSaveCardView.getLayoutParams();
        layoutParams.height = (int) getBottomSheetDialogDefaultHeight();
        myPlanSaveCardView.setLayoutParams(layoutParams);
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case STATE_COLLAPSED:
                        break;
                    case STATE_EXPANDED:
                        break;
                    case STATE_HIDDEN:
                        finish();
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }

    private double getBottomSheetDialogDefaultHeight() {
        return getWindowHeight() * BOTTOM_SHEET_SIZE_EXPENDED_PERCENT;
    }

    private int getWindowHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}
