package com.koreatech.naeilro.ui.facility.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.entity.reports.Reports;
import com.koreatech.naeilro.ui.reports.adapter.ReportsImageRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class FacilityImageRecyclerViewAdapter extends RecyclerView.Adapter<FacilityImageRecyclerViewAdapter.ViewHolder>{
    private List<Facility> facilityList;

    public FacilityImageRecyclerViewAdapter(List<Facility> list) {
        facilityList = new ArrayList<>();
        facilityList.addAll(list);
    }

    @NonNull
    @Override
    public FacilityImageRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_view, parent, false);
        return new FacilityImageRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Facility facility = facilityList.get(position);
        holder.setIsRecyclable(false);
        if(facility.getOriginimgurl() != null)
            Glide.with(holder.facilityImageView).load(facility.getOriginimgurl()).into(holder.facilityImageView);

    }

    @Override
    public int getItemCount() {
        return facilityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView facilityImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            facilityImageView = itemView.findViewById(R.id.reports_image_view);
        }
    }
}
