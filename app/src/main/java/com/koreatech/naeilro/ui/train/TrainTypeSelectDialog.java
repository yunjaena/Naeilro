package com.koreatech.naeilro.ui.train;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.traininfo.TrainInfo;
import com.koreatech.naeilro.ui.train.adapter.TrainTypeRecyclerViewAdapter;

import java.util.List;

public class TrainTypeSelectDialog extends Dialog {
    private DialogCallback dialogCallback;
    private List<TrainInfo> trainInfoList;
    private RecyclerView dialogRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TrainTypeRecyclerViewAdapter trainTypeRecyclerViewAdapter;

    private TrainTypeSelectDialog(@NonNull Context context) {
        super(context);
    }

    public TrainTypeSelectDialog(@NonNull Context context, DialogCallback dialogCallback, List<TrainInfo> trainInfoList) {
        super(context);
        this.dialogCallback = dialogCallback;
        this.trainInfoList = trainInfoList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.dialog_train_type_select);
        init();

    }

    public void init() {
        dialogRecyclerView = findViewById(R.id.train_type_select_dialog_recycler_view);
        initRecyclerView();
    }


    public void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        trainTypeRecyclerViewAdapter = new TrainTypeRecyclerViewAdapter(trainInfoList);
        trainTypeRecyclerViewAdapter.setRecyclerViewClickListener(new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                dialogCallback.trainItemSelect(position);
                dismiss();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        });
        dialogRecyclerView.setLayoutManager(linearLayoutManager);
        dialogRecyclerView.setAdapter(trainTypeRecyclerViewAdapter);
        trainTypeRecyclerViewAdapter.notifyDataSetChanged();
    }

    public interface DialogCallback {
        void trainItemSelect(int index);
    }

}
