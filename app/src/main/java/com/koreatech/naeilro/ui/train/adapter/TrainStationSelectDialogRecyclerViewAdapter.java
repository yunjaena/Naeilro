package com.koreatech.naeilro.ui.train.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.trainstaion.TrainStationInfo;

import java.util.List;

public class TrainStationSelectDialogRecyclerViewAdapter extends RecyclerView.Adapter<TrainStationSelectDialogRecyclerViewAdapter.ViewHolder> {
    private List<TrainStationInfo> trainStationInfoList;
    private RecyclerViewClickListener recyclerViewClickListener;

    public TrainStationSelectDialogRecyclerViewAdapter(List<TrainStationInfo> trainStationInfoList){
        this.trainStationInfoList = trainStationInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_train_station, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrainStationInfo trainStationInfo = trainStationInfoList.get(position);
        holder.trainStationTextView.setText(trainStationInfo.getStationName());
        holder.trainStationTextView.setOnClickListener(v -> {
            if (recyclerViewClickListener != null)
                recyclerViewClickListener.onClick(holder.trainStationTextView, position);
        });
    }

    @Override
    public int getItemCount() {
        return trainStationInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView trainStationTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trainStationTextView = itemView.findViewById(R.id.train_station_item_text_view);
        }
    }

    public void setRecyclerViewClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }
}
