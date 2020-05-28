package com.koreatech.naeilro.ui.train.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.traininfo.TrainInfo;
import com.koreatech.naeilro.network.entity.trainstaion.TrainStationInfo;

import java.util.List;

public class TrainTypeRecyclerViewAdapter extends RecyclerView.Adapter<TrainTypeRecyclerViewAdapter.ViewHolder> {
    private List<TrainInfo> trainInfoList;
    private RecyclerViewClickListener recyclerViewClickListener;

    public TrainTypeRecyclerViewAdapter(List<TrainInfo> trainInfoList){
        this.trainInfoList = trainInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_train_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrainInfo trainInfo = trainInfoList.get(position);
        holder.trainTypeTextView.setText(trainInfo.getVehicleName());
        holder.trainTypeTextView.setOnClickListener(v -> {
            if (recyclerViewClickListener != null)
                recyclerViewClickListener.onClick(holder.trainTypeTextView, position);
        });
    }

    @Override
    public int getItemCount() {
        return trainInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView trainTypeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trainTypeTextView = itemView.findViewById(R.id.train_type_item_text_view);
        }
    }

    public void setRecyclerViewClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }
}
