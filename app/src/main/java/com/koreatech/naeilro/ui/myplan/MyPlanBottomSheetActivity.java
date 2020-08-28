package com.koreatech.naeilro.ui.myplan;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.koreatech.core.activity.ActivityBase;
import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.myplan.MyPlan;
import com.koreatech.naeilro.network.entity.myplan.MyPlanNode;
import com.koreatech.naeilro.network.interactor.MyPlanRestInteractor;
import com.koreatech.naeilro.ui.myplan.adapter.MyPlanCollectionAdapter;
import com.koreatech.naeilro.ui.myplan.presenter.MyPlanBottomSheetContract;
import com.koreatech.naeilro.ui.myplan.presenter.MyPlanBottomSheetPresenter;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;

public class MyPlanBottomSheetActivity extends ActivityBase implements MyPlanBottomSheetContract.View {
    public static final String TAG = "MyPlanBottomSheetActivity";
    public static final String CONTENT_ID = "CONTENT_ID";
    public static final String CONTENT_TYPE = "CONTENT_TYPE";
    public final double BOTTOM_SHEET_SIZE_SHOW_PERCENT = 0.5;
    public final double BOTTOM_SHEET_SIZE_EXPENDED_PERCENT = 0.95;
    private LinearLayout myPlanSaveCardView;
    private LinearLayout myPlanCollectionAddLinearLayout;
    private RecyclerView myPlanCollectionRecyclerView;
    private MyPlanCollectionAdapter myPlanCollectionAdapter;
    private BottomSheetBehavior behavior;
    private Context context;
    private List<MyPlan> myPlanList;
    private LinearLayoutManager linearLayoutManager;
    private MyPlanBottomSheetPresenter myPlanBottomSheetPresenter;
    private MyPlanNode selectedPlanNode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan_bottom_sheet);
        String contentID = getIntent().getStringExtra(CONTENT_ID);
        String contentType = getIntent().getStringExtra(CONTENT_TYPE);
        selectedPlanNode = new MyPlanNode(contentType, contentID);
        init();
    }

    private void init() {
        context = this;
        myPlanList = new ArrayList<>();
        myPlanBottomSheetPresenter = new MyPlanBottomSheetPresenter(this, new MyPlanRestInteractor());
        myPlanSaveCardView = findViewById(R.id.my_plan_save_card_view);
        myPlanCollectionAddLinearLayout = findViewById(R.id.my_plan_collection_add_linear_layout);
        myPlanCollectionRecyclerView = findViewById(R.id.my_plan_collection_recycler_view);
        myPlanCollectionAddLinearLayout.setOnClickListener(view -> myPlanCollectionAddButtonClicked());
        setBottomSheet();
        setBottomSheetRecyclerView();
        myPlanBottomSheetPresenter.getMyPlanCollectionList(selectedPlanNode);
    }

    private void setBottomSheetRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this);
        myPlanCollectionAdapter = new MyPlanCollectionAdapter(myPlanList);
        myPlanCollectionAdapter.setRecyclerViewClickListener(new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (myPlanList.get(position).isContainPlan()) {
                    if (!getSelectedPlanNodeId(myPlanList.get(position)).isEmpty())
                        myPlanBottomSheetPresenter.removeNode(getSelectedPlanNodeId(myPlanList.get(position)));
                }
                else {
                        myPlanBottomSheetPresenter.createNode(myPlanList.get(position).getPlanNumber(), selectedPlanNode);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        myPlanCollectionRecyclerView.setLayoutManager(linearLayoutManager);
        myPlanCollectionRecyclerView.setAdapter(myPlanCollectionAdapter);
        myPlanCollectionAdapter.notifyDataSetChanged();
    }

    public String getSelectedPlanNodeId(MyPlan myPlan) {
        for (MyPlanNode myPlanNode : myPlan.getMyPlanNodeList()) {
            if (myPlanNode.getContentType().equals(selectedPlanNode.getContentType()) && myPlanNode.getContendID().equals(selectedPlanNode.getContendID())) {
                return myPlanNode.getNodeNumber();
            }
        }
        return "";
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
        showCreateMyPlanDialog();
    }

    private double getBottomSheetDialogDefaultHeight() {
        return getWindowHeight() * BOTTOM_SHEET_SIZE_EXPENDED_PERCENT;
    }

    private int getWindowHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    private void showCreateMyPlanDialog() {
        final EditText editText = new EditText(this);
        FrameLayout container = new FrameLayout(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.dialog_margin);
        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.dialog_margin);
        editText.setLayoutParams(params);
        container.addView(editText);
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle(R.string.my_plan_collection_dialog_title).setMessage(R.string.my_plan_collection_dialog_detail)
                .setIcon(R.mipmap.ic_add_peach_light).setCancelable(false).setView(container).setPositiveButton("확인",
                (dialog, id) -> {
                    String value = editText.getText().toString();
                    addMyPlan(value);
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();

    }

    public void addMyPlan(String name) {
        if (name.isEmpty()) {
            ToastUtil.getInstance().makeShort(R.string.my_plan_collection_empty_string_warning);
            return;
        }
        myPlanBottomSheetPresenter.createPlan(name);

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
    public void showSuccessCreatePlan() {
        myPlanBottomSheetPresenter.getMyPlanCollectionList(selectedPlanNode);
    }

    @Override
    public void showMyPlanCollection(List<MyPlan> myPlanList) {
        this.myPlanList.clear();
        this.myPlanList.addAll(myPlanList);
        myPlanCollectionAdapter.notifyDataSetChanged();
    }

    @Override
    public void failGetPlanInfo() {
        finish();
    }

    @Override
    public void setPresenter(MyPlanBottomSheetPresenter presenter) {
        this.myPlanBottomSheetPresenter = presenter;
    }
}
