package com.koreatech.naeilro.ui.reports.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.reports.Reports;
import com.koreatech.naeilro.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class ReportsInfoRecyclerViewAdapter extends RecyclerView.Adapter<ReportsInfoRecyclerViewAdapter.ViewHolder>{
    private List<Reports> reportsList;
    NavController navController;
    Context context;

    @NonNull
    @Override
    public ReportsInfoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_house_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsInfoRecyclerViewAdapter.ViewHolder holder, int position) {
        Reports reports = reportsList.get(position);
        holder.setIsRecyclable(false);
        if(reports.getTitle() != null)
            holder.reportsTitleTextView.setText(reports.getTitle());
        else
            holder.reportsTitleTextView.setText("레포츠명이 등록되지 않았습니다.");
        if(reports.getAddr1() != null)
            holder.reportsAddressTextView.setText(reports.getAddr1());
        else
            holder.reportsAddressTextView.setText("");
        if(reports.getTitle() != null)
            holder.reportsTelTextView.setText(reports.getTel());
        else
            holder.reportsTelTextView.setText("");
        if(reports.getFirstimage() != null)
            Glide.with(holder.reportsImageView)
                    .load(reports.getFirstimage())
                    .thumbnail(0.05f)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .error(R.drawable.ic_no_image)
                    .into(holder.reportsImageView);
        else
            Glide.with(holder.reportsImageView).load(R.drawable.ic_no_image).into(holder.reportsImageView);

        holder.view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("contentId", Integer.parseInt(reports.getContentid()));
                bundle.putString("title", reports.getTitle());
                navController.navigate(R.id.action_navigation_reports_to_navigation_detail_reports, bundle);
            }
        });


    }
    public void addItem(List<Reports> item){
        for(int i=0;i<item.size();i++){
            reportsList.add(item.get(i));
        }
        notifyDataSetChanged();
    }
    public void clearItem(){
        reportsList.clear();
    }


    @Override
    public int getItemCount() {
        return reportsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView reportsTitleTextView;
        public TextView reportsAddressTextView;
        public TextView reportsTelTextView;
        public ImageView reportsImageView;
        public final View view;

        public ViewHolder(@NonNull View itemView ) {
            super(itemView);
            view = itemView;
            reportsTitleTextView = itemView.findViewById(R.id.house_title);
            reportsAddressTextView = itemView.findViewById(R.id.house_address);
            reportsTelTextView = itemView.findViewById(R.id.house_tel);
            reportsImageView = itemView.findViewById(R.id.house_image_view);
        }
    }

    public ReportsInfoRecyclerViewAdapter(Context context) {
        this.context = context;
        reportsList = new ArrayList<>();
        navController = Navigation.findNavController((MainActivity)context, R.id.nav_host_fragment);

    }
}
