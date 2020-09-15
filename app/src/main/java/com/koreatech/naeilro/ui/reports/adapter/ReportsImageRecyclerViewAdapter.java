package com.koreatech.naeilro.ui.reports.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.reports.Reports;

import java.util.ArrayList;
import java.util.List;

import static com.koreatech.naeilro.ui.reports.ReportsDetailFragment.fromHtml;

public class ReportsImageRecyclerViewAdapter extends RecyclerView.Adapter<ReportsImageRecyclerViewAdapter.ViewHolder>{
    private List<Reports> reportsList;

    public ReportsImageRecyclerViewAdapter(List<Reports> list) {
        reportsList = new ArrayList<>();
        reportsList.addAll(list);
    }

    @NonNull
    @Override
    public ReportsImageRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_view, parent, false);
        return new ReportsImageRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsImageRecyclerViewAdapter.ViewHolder holder, int position) {
        Reports reports = reportsList.get(position);
        holder.setIsRecyclable(false);
        if(reports.getOriginimgurl() != null)
            Glide.with(holder.reportsImageView).load(reports.getOriginimgurl()).into(holder.reportsImageView);

    }

    @Override
    public int getItemCount() {
        return reportsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView reportsImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reportsImageView = itemView.findViewById(R.id.reports_image_view);
        }
    }
}
