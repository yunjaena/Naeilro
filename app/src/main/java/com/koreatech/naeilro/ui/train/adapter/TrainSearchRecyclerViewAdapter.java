package com.koreatech.naeilro.ui.train.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.trainsearch.TrainSearchInfo;

import java.util.List;

public class TrainSearchRecyclerViewAdapter extends RecyclerView.Adapter<TrainSearchRecyclerViewAdapter.ViewHolder> {
    private List<TrainSearchInfo> trainSearchInfoList;

    public TrainSearchRecyclerViewAdapter(List<TrainSearchInfo> trainSearchInfoList) {
        this.trainSearchInfoList = trainSearchInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_train_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrainSearchInfo trainSearchInfo = trainSearchInfoList.get(position);
        String departTime = trainSearchInfo.getDepartureTime().substring(8, 10) + ":" + trainSearchInfo.getDepartureTime().substring(10, 12);
        String arrivalTime = trainSearchInfo.getArrivalTime().substring(8, 10) + ":" + trainSearchInfo.getArrivalTime().substring(10, 12);
        holder.trainDepartTimeTextView.setText(departTime);
        holder.trainArrivalTimeTextView.setText(arrivalTime);
        holder.trainTypeTextView.setText(trainSearchInfo.getTrainName());
        holder.trainPriceTextView.setText(String.valueOf(trainSearchInfo.getAdultCharge()));
        holder.trainNumberTextView.setText(String.valueOf(trainSearchInfo.getTrainNumber()));

    }

    @Override
    public int getItemCount() {
        return trainSearchInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView trainDepartTimeTextView;
        public TextView trainArrivalTimeTextView;
        public TextView trainTypeTextView;
        public TextView trainNumberTextView;
        public TextView trainPriceTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trainDepartTimeTextView = itemView.findViewById(R.id.train_search_item_departure_text_view);
            trainArrivalTimeTextView = itemView.findViewById(R.id.train_search_item_arrival_text_view);
            trainTypeTextView = itemView.findViewById(R.id.train_search_item_train_type_text_view);
            trainNumberTextView = itemView.findViewById(R.id.train_search_item_train_number_text_view);
            trainPriceTextView = itemView.findViewById(R.id.train_search_item_train_price_text_view);
        }
    }
}
