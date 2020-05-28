package com.koreatech.naeilro.ui.train;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.trainstaion.TrainStationInfo;
import com.koreatech.naeilro.ui.train.adapter.TrainStationSelectDialogRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class TrainStationSelectDialog extends Dialog {
    private boolean isDepart;
    private DialogCallback dialogCallback;
    private List<TrainStationInfo> trainStationInfoList;
    private List<TrainStationInfo> trainStationSearchInfoList;
    private RecyclerView dialogRecyclerView;
    private TextView dialogTitleTextView;
    private TextView dialogSearchEditText;
    private LinearLayoutManager linearLayoutManager;
    private TrainStationSelectDialogRecyclerViewAdapter trainStationSelectDialogRecyclerViewAdapter;

    private TrainStationSelectDialog(@NonNull Context context) {
        super(context);
    }

    public TrainStationSelectDialog(@NonNull Context context, boolean isDepart, DialogCallback dialogCallback, List<TrainStationInfo> trainStationInfoList) {
        super(context);
        this.isDepart = isDepart;
        this.dialogCallback = dialogCallback;
        this.trainStationInfoList = trainStationInfoList;
        trainStationSearchInfoList = new ArrayList<>();
        trainStationSearchInfoList.addAll(trainStationInfoList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.dialog_train_station_select);
        init();

    }

    public void init() {
        dialogRecyclerView = findViewById(R.id.train_station_select_dialog_recycler_view);
        dialogTitleTextView = findViewById(R.id.train_station_select_dialog_text_view);
        dialogSearchEditText = findViewById(R.id.train_station_select_dialog_edit_text);
        if (isDepart) {
            dialogTitleTextView.setText(R.string.train_select_arrival_station);
        } else {
            dialogTitleTextView.setText(R.string.train_select_arrival_station);
        }
        dialogSearchEditText.addTextChangedListener(new TextWatcher() {
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
        initRecyclerView();
    }

    public void updateRecyclerViewItem() {
        String searchText = dialogSearchEditText.getText().toString().trim();
        trainStationSearchInfoList.clear();
        if (searchText.isEmpty()) {
            trainStationSearchInfoList.addAll(trainStationInfoList);
        } else {
            for (int i = 0; i < trainStationInfoList.size(); i++) {
                if (trainStationInfoList.get(i).getStationName().contains(searchText)) {
                    trainStationSearchInfoList.add(trainStationInfoList.get(i));
                }
            }
        }
        trainStationSelectDialogRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        trainStationSelectDialogRecyclerViewAdapter = new TrainStationSelectDialogRecyclerViewAdapter(trainStationSearchInfoList);
        trainStationSelectDialogRecyclerViewAdapter.setRecyclerViewClickListener(new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (dialogCallback != null) {
                    for (int i = 0; i < trainStationInfoList.size(); i++) {
                        if (trainStationInfoList.get(i).getStationName().equals(trainStationSearchInfoList.get(position).getStationName())) {
                            dialogCallback.stationItemSelect(i, isDepart);
                            dismiss();
                            return;
                        }
                    }

                }
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        });
        dialogRecyclerView.setLayoutManager(linearLayoutManager);
        dialogRecyclerView.setAdapter(trainStationSelectDialogRecyclerViewAdapter);
        trainStationSelectDialogRecyclerViewAdapter.notifyDataSetChanged();
    }

    public interface DialogCallback {
        void stationItemSelect(int index, boolean isDepart);
    }

}
