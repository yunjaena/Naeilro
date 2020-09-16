package com.koreatech.naeilro.ui.myplan;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.koreatech.core.activity.ActivityBase;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.myplan.RecommendPath;
import com.koreatech.naeilro.network.interactor.MyPlanRestInteractor;
import com.koreatech.naeilro.ui.myplan.adapter.MyPlanPathAdapter;
import com.koreatech.naeilro.ui.myplan.presenter.MyPlanRecommendPathContract;
import com.koreatech.naeilro.ui.myplan.presenter.MyPlanRecommendPathPresenter;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;

public class MyPlanRecommendPathActivity extends ActivityBase implements MyPlanRecommendPathContract.View {
    private static final double centerLon = 127.48318433761597;
    private static final double centerLat = 36.41592967015607;
    public final double BOTTOM_SHEET_SIZE_SHOW_PERCENT = 0.95;
    public final double BOTTOM_SHEET_SIZE_EXPENDED_PERCENT = 0.95;
    public final double T_Map_HEIGHT_PERCENT = 0.4;
    private LinearLayout tMapLinearLayout;
    private LinearLayout departureAndArrivalLinearLayout;
    private RecyclerView pathRecyclerView;
    private MyPlanPathAdapter myPlanPathAdapter;
    private TMapView tMapView;
    private BottomSheetBehavior behavior;
    private List<RecommendPath> recommendPathList;
    private String planTitle;
    private String planNumber;
    private String departNodeNumber;
    private String arrivalNodeNumber;
    private MyPlanRecommendPathPresenter myPlanRecommendPathPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan_recommend_path);
        planTitle = getIntent().getStringExtra("PLAN_TITLE");
        arrivalNodeNumber = getIntent().getStringExtra("ARRIVAL");
        departNodeNumber = getIntent().getStringExtra("DEPARTURE");
        planNumber = getIntent().getStringExtra("PLAN_NO");
        init();
    }

    private void init() {
        myPlanRecommendPathPresenter = new MyPlanRecommendPathPresenter(this, new MyPlanRestInteractor());
        if (planTitle != null) {
            ((TextView) findViewById(R.id.plan_title_text_view)).setText(planTitle);
        }
        initTMapView();
        initBottomSheet();
        initRecyclerView();
        myPlanRecommendPathPresenter.getRecommendPath(planNumber, departNodeNumber, arrivalNodeNumber);
    }

    private void initTMapView() {
        tMapLinearLayout = findViewById(R.id.t_map_linear_layout);
        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(NaeilroApplication.getTMapApiKey());
        tMapView.setZoomLevel(6);
        tMapView.setCenterPoint(centerLon, centerLat);
        ViewGroup.LayoutParams layoutParams = tMapLinearLayout.getLayoutParams();
        layoutParams.height = (int) getTMapDefaultHeight();
        tMapLinearLayout.setLayoutParams(layoutParams);
        tMapLinearLayout.addView(tMapView);
    }

    private void initBottomSheet() {
        departureAndArrivalLinearLayout = findViewById(R.id.my_plan_departure_and_arrival_linear_layout);
        int maxHeight = (int) (getWindowHeight() * BOTTOM_SHEET_SIZE_SHOW_PERCENT);
        behavior = BottomSheetBehavior.from(departureAndArrivalLinearLayout);
        behavior.setPeekHeight(maxHeight);
        ViewGroup.LayoutParams layoutParams = departureAndArrivalLinearLayout.getLayoutParams();
        layoutParams.height = (int) getBottomSheetDialogDefaultHeight();
        departureAndArrivalLinearLayout.setLayoutParams(layoutParams);
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
        recommendPathList = new ArrayList<>();
        pathRecyclerView = findViewById(R.id.path_recycler_view);
        myPlanPathAdapter = new MyPlanPathAdapter(recommendPathList, this);
        pathRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pathRecyclerView.setAdapter(myPlanPathAdapter);
    }


    private double getTMapDefaultHeight() {
        return getWindowHeight() * T_Map_HEIGHT_PERCENT;
    }

    private double getBottomSheetDialogDefaultHeight() {
        return getWindowHeight() * BOTTOM_SHEET_SIZE_EXPENDED_PERCENT;
    }

    private int getWindowHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    @Override
    public void showMessage(String message) {
        ToastUtil.getInstance().makeShort(message);
    }

    @Override
    public void showMessage(int message) {
        ToastUtil.getInstance().makeShort(message);
    }

    @Override
    public void showLoading() {
        showProgressDialog(R.string.loading);
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void showRecommendPath(List<RecommendPath> recommendPathList) {
        this.recommendPathList.addAll(recommendPathList);
        myPlanPathAdapter.notifyDataSetChanged();
    }

    @Override
    public void failGetRecommendPath() {
        behavior.setState(STATE_HIDDEN);
    }

    @Override
    public void setPresenter(MyPlanRecommendPathPresenter presenter) {
        this.myPlanRecommendPathPresenter = presenter;
    }
}
