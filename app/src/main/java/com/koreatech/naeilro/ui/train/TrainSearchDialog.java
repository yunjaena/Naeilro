package com.koreatech.naeilro.ui.train;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.traininfo.TrainInfo;
import com.koreatech.naeilro.network.entity.trainsearch.TrainSearchInfo;
import com.koreatech.naeilro.ui.train.adapter.TrainSearchRecyclerViewAdapter;
import com.koreatech.naeilro.ui.train.adapter.TrainTypeRecyclerViewAdapter;

import java.util.List;

public class TrainSearchDialog extends Dialog {
    private List<TrainSearchInfo> trainSearchInfoList;
    private RecyclerView dialogRecyclerView;
    private TextView departureDate;
    private TextView arrivalStation;
    private TextView departureStation;
    private LinearLayoutManager linearLayoutManager;
    private TrainSearchRecyclerViewAdapter trainSearchRecyclerViewAdapter;

    private TrainSearchDialog(@NonNull Context context) {
        super(context);
    }

    public TrainSearchDialog(@NonNull Context context, List<TrainSearchInfo> trainSearchInfoList) {
        super(context);
        this.trainSearchInfoList = trainSearchInfoList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.dialog_train_search);
        init();

    }

    public void init() {
        String date = trainSearchInfoList.get(0).getArrivalTime().substring(0, 4) + "." + trainSearchInfoList.get(0).getArrivalTime().substring(4, 6) + "." +trainSearchInfoList.get(0).getArrivalTime().substring(6, 8);
        dialogRecyclerView = findViewById(R.id.train_search_dialog_recycler_view);
        departureStation = findViewById(R.id.train_search_dialog_departure_text_view);
        arrivalStation = findViewById(R.id.train_search_dialog_arrival_text_view);
        departureDate = findViewById(R.id.train_search_dialog_departure_date_text_view);
        arrivalStation.setText(trainSearchInfoList.get(0).getArrivalPlaceName());
        departureStation.setText(trainSearchInfoList.get(0).getDeparturePlaceName());
        departureDate.setText(date);
        initRecyclerView();
    }


    public void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        trainSearchRecyclerViewAdapter = new TrainSearchRecyclerViewAdapter(trainSearchInfoList);
        dialogRecyclerView.setLayoutManager(linearLayoutManager);
        dialogRecyclerView.setAdapter(trainSearchRecyclerViewAdapter);
        trainSearchRecyclerViewAdapter.notifyDataSetChanged();
    }


}
