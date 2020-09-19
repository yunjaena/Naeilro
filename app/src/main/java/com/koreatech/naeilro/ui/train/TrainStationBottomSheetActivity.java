package com.koreatech.naeilro.ui.train;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.github.windsekirun.koreanindexer.KoreanIndexerRecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.koreatech.core.activity.ActivityBase;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.ui.train.adapter.TrainBottomSheetAdapter;

import java.util.ArrayList;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;

public class TrainStationBottomSheetActivity extends ActivityBase {
    public final double BOTTOM_SHEET_SIZE_SHOW_PERCENT = 0.6;
    public final double BOTTOM_SHEET_SIZE_EXPENDED_PERCENT = 0.9;
    private LinearLayout bottomSheetCardView;
    private BottomSheetBehavior behavior;
    private TextView titleTextView;
    private TextView recyclerViewTitleTextView;
    private EditText inputEditText;
    private KoreanIndexerRecyclerView koreanIndexerRecyclerView;
    private TrainBottomSheetAdapter trainBottomSheetAdapter;
    private ArrayList<String> stringList;
    private String title;
    private String inputHint;
    private String recyclerViewTitleText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_station_bottom_sheet);
        stringList = new ArrayList<>();
        stringList.addAll(getIntent().getStringArrayListExtra("LIST"));
        title = getIntent().getStringExtra("TITLE");
        inputHint = getIntent().getStringExtra("INPUT");
        recyclerViewTitleText = getIntent().getStringExtra("RECYCLER_VIEW_TEXT");
        init();
    }

    private void init() {
        initView();
        initBottomSheet();
        initRecyclerView();
    }

    private void initView() {
        bottomSheetCardView = findViewById(R.id.train_card_view);
        titleTextView = findViewById(R.id.title);
        inputEditText = findViewById(R.id.search_edit_text);
        koreanIndexerRecyclerView = findViewById(R.id.bottom_sheet_recycler_view);
        recyclerViewTitleTextView = findViewById(R.id.recycler_view_title_text_view);
        if (title != null)
            titleTextView.setText(title);
        if (inputHint != null)
            inputEditText.setHint(inputHint);
        if (recyclerViewTitleText != null)
            recyclerViewTitleTextView.setHint(inputHint);
    }

    private void initBottomSheet() {
        int maxHeight = (int) (getWindowHeight() * BOTTOM_SHEET_SIZE_SHOW_PERCENT);
        behavior = BottomSheetBehavior.from(bottomSheetCardView);
        behavior.setPeekHeight(maxHeight);
        ViewGroup.LayoutParams layoutParams = bottomSheetCardView.getLayoutParams();
        layoutParams.height = (int) getBottomSheetDialogDefaultHeight();
        bottomSheetCardView.setLayoutParams(layoutParams);
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

    private void initRecyclerView() {
        trainBottomSheetAdapter = new TrainBottomSheetAdapter(this, stringList);
        koreanIndexerRecyclerView.setKeywordList(stringList);
        koreanIndexerRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        koreanIndexerRecyclerView.setOnItemClickListener(i -> {
            Intent intent = new Intent();
            intent.putExtra("RESULT", i);
            setResult(RESULT_OK, intent);
            behavior.setState(STATE_HIDDEN);
        });
        koreanIndexerRecyclerView.setAdapter(trainBottomSheetAdapter);

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
