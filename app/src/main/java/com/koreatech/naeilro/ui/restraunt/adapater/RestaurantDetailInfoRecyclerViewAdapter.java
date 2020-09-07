package com.koreatech.naeilro.ui.restraunt.adapater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantInfo;
import com.koreatech.naeilro.ui.facility.adapter.FacilityDetailInfoRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailInfoRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantDetailInfoRecyclerViewAdapter.ViewHolder>{
    private List<RestaurantInfo> restaurantList;

    public RestaurantDetailInfoRecyclerViewAdapter(List<RestaurantInfo> restaurantList) {
        this.restaurantList = new ArrayList<>();
        restaurantList.addAll(restaurantList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reports_detail_info, parent, false);
        return new RestaurantDetailInfoRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView infoName;
        private TextView infoText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            infoName = itemView.findViewById(R.id.info_name);
            infoText = itemView.findViewById(R.id.info_text);
        }
    }
}
