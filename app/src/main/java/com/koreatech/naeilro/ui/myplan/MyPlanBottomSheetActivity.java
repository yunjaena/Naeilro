package com.koreatech.naeilro.ui.myplan;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.koreatech.core.activity.ActivityBase;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.ui.myplan.adapter.MyPlanCollectionAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;

public class MyPlanBottomSheetActivity extends ActivityBase {
    public static final String TAG = "MyPlanBottomSheetActivity";
    public final double BOTTOM_SHEET_SIZE_SHOW_PERCENT = 0.5;
    public final double BOTTOM_SHEET_SIZE_EXPENDED_PERCENT = 0.95;
    private LinearLayout myPlanSaveCardView;
    private LinearLayout myPlanCollectionAddLinearLayout;
    private RecyclerView myPlanCollectionRecyclerView;
    private MyPlanCollectionAdapter myPlanCollectionAdapter;
    private BottomSheetBehavior behavior;
    private Context context;
    private List<String> collectionList;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan_bottom_sheet);
        init();
    }

    private void init() {
        context = this;
        collectionList = new ArrayList<>();
        myPlanSaveCardView = findViewById(R.id.my_plan_save_card_view);
        myPlanCollectionAddLinearLayout = findViewById(R.id.my_plan_collection_add_linear_layout);
        myPlanCollectionRecyclerView = findViewById(R.id.my_plan_collection_recycler_view);
        myPlanCollectionAddLinearLayout.setOnClickListener(view -> myPlanCollectionAddButtonClicked());
        setBottomSheet();
        setBottomSheetRecyclerView();
    }

    private void setBottomSheetRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this);
        myPlanCollectionAdapter = new MyPlanCollectionAdapter(collectionList);
        myPlanCollectionRecyclerView.setLayoutManager(linearLayoutManager);
        myPlanCollectionRecyclerView.setAdapter(myPlanCollectionAdapter);
        collectionList.add("1");
        collectionList.add("2");
        collectionList.add("3");
        collectionList.add("4");
        collectionList.add("5");
        collectionList.add("6");
        collectionList.add("7");
        collectionList.add("8");
        collectionList.add("9");
        collectionList.add("10");
        collectionList.add("11");
        collectionList.add("12");
        collectionList.add("13");
        collectionList.add("14");
        collectionList.add("15");
        collectionList.add("16");
        collectionList.add("17");
        collectionList.add("18");
        myPlanCollectionAdapter.notifyDataSetChanged();
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

    private void myPlanCollectionAddButtonClicked() {
        // TODO -> my plan collection add function
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
