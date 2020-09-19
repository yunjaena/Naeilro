package com.koreatech.naeilro.ui.train;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.koreatech.core.activity.ActivityBase;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.ui.koreanindexer.KoreanIndexerRecyclerView;
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
    private ArrayList<String> searchStringList;
    private String title;
    private String inputHint;
    private String recyclerViewTitleText;
    private boolean isSectionUse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_station_bottom_sheet);
        stringList = new ArrayList<>();
        searchStringList = new ArrayList<>();
        stringList.addAll(getIntent().getStringArrayListExtra("LIST"));
        searchStringList.addAll(stringList);
        isSectionUse = getIntent().getBooleanExtra("SECTION_USE", false);
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
            recyclerViewTitleTextView.setHint(recyclerViewTitleText);
        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateRecyclerViewItem();
            }
        });
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
                        overridePendingTransition(0, 0);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }

    private void initRecyclerView() {
        trainBottomSheetAdapter = new TrainBottomSheetAdapter(this, searchStringList);
        koreanIndexerRecyclerView.setKeywordList(searchStringList);
        koreanIndexerRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        koreanIndexerRecyclerView.setOnItemClickListener(i -> {
            Intent intent = new Intent();
            for (int index = 0; index < stringList.size(); index++) {
                if (stringList.get(index).equals(searchStringList.get(i))) {
                    intent.putExtra("RESULT", index);
                    setResult(RESULT_OK, intent);
                    behavior.setState(STATE_HIDDEN);
                    return;
                }
            }
        });
        koreanIndexerRecyclerView.setAdapter(trainBottomSheetAdapter);
        updateSection();
    }

    public void updateRecyclerViewItem() {
        String searchText = inputEditText.getText().toString().trim();
        searchStringList.clear();
        if (searchText.isEmpty()) {
            searchStringList.addAll(stringList);
        } else {
            for (int i = 0; i < stringList.size(); i++) {
                if (stringList.get(i).contains(searchText)) {
                    searchStringList.add(stringList.get(i));
                }
            }
        }
        trainBottomSheetAdapter.notifyDataSetChanged();
        updateSection();
    }

    private void updateSection() {
        if (isSectionUse) {
            if (inputEditText.getText().length() > 0)
                koreanIndexerRecyclerView.setUseSection(false);
            else
                koreanIndexerRecyclerView.setUseSection(true);
        } else {
            koreanIndexerRecyclerView.setUseSection(false);
        }
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
