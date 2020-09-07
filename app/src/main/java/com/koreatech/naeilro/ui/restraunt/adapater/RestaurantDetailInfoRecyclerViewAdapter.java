package com.koreatech.naeilro.ui.restraunt.adapater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantInfo;
import com.koreatech.naeilro.ui.facility.adapter.FacilityDetailInfoRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.koreatech.naeilro.ui.reports.ReportsDetailFragment.fromHtml;

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
        RestaurantInfo restaurantInfo = restaurantList.get(position);
        holder.setIsRecyclable(false);
        if(restaurantInfo.getInfoname() != null)
            holder.infoName.setText(restaurantInfo.getInfoname());
        else
            holder.infoName.setText("");
        if(restaurantInfo.getInfotext() != null)
            holder.infoText.setText(fromHtml(restaurantInfo.getInfotext()));
        else
            holder.infoText.setText("");
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
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
