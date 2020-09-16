package com.koreatech.naeilro.ui.myplan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.koreatech.core.activity.ActivityBase;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.myplan.MyPlanNode;

import java.util.ArrayList;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;

public class MyPlanSelectDepartureAndArrivalActivity extends ActivityBase {
    private LinearLayout departureAndArrivalLinearLayout;
    private Spinner departureSpinner;
    private Spinner arrivalSpinner;
    private Button recommendPathButton;
    private BottomSheetBehavior behavior;
    private ArrayList<MyPlanNode> nodeList;
    private String planNumber;
    private String planTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan_select_departure_and_arrival);
        nodeList = getIntent().getExtras().getParcelableArrayList("NODE_LIST");
        planNumber = getIntent().getStringExtra("PLAN_NO");
        planTitle = getIntent().getStringExtra("PLAN_TITLE");
        init();
    }

    public void init() {
        departureAndArrivalLinearLayout = findViewById(R.id.my_plan_departure_and_arrival_linear_layout);
        departureSpinner = findViewById(R.id.my_plan_departure_spinner);
        arrivalSpinner = findViewById(R.id.my_plan_arrival_spinner);
        recommendPathButton = findViewById(R.id.my_plan_get_recommend_path_button);
        recommendPathButton.setOnClickListener(this::onClickedRecommendPathButton);
        initBottomSheet();
        initSpinner();
    }

    public void initSpinner() {
        String[] list = new String[nodeList.size()];
        for (int i = 0; i < nodeList.size(); i++) {
            list[i] = nodeList.get(i).getTitle();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departureSpinner.setAdapter(adapter);
        arrivalSpinner.setAdapter(adapter);
    }

    public void initBottomSheet() {
        behavior = BottomSheetBehavior.from(departureAndArrivalLinearLayout);
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

    public void onClickedRecommendPathButton(View view) {
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(departureAndArrivalLinearLayout);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case STATE_COLLAPSED:
                    case STATE_EXPANDED:
                        break;
                    case STATE_HIDDEN:
                        finish();
                        goToMyPlanRecommend();
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
        bottomSheetBehavior.setState(STATE_HIDDEN);
    }

    public void goToMyPlanRecommend() {
        Intent intent = new Intent(this, MyPlanRecommendPathActivity.class);
        String arrivalNodeNumber = nodeList.get(arrivalSpinner.getSelectedItemPosition()).getNodeNumber();
        String departureNodeNumber = nodeList.get(departureSpinner.getSelectedItemPosition()).getNodeNumber();
        intent.putExtra("ARRIVAL", arrivalNodeNumber);
        intent.putExtra("DEPARTURE", departureNodeNumber);
        intent.putExtra("PLAN_NO", planNumber);
        intent.putExtra("PLAN_TITLE", planTitle);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        behavior.setState(STATE_HIDDEN);
    }
}
