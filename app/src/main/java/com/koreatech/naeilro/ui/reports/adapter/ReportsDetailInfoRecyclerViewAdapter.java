package com.koreatech.naeilro.ui.reports.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.reports.Reports;

import java.util.ArrayList;
import java.util.List;

import static com.koreatech.naeilro.ui.reports.ReportsDetailFragment.fromHtml;

public class ReportsDetailInfoRecyclerViewAdapter extends RecyclerView.Adapter<ReportsDetailInfoRecyclerViewAdapter.ViewHolder>{
    private List<Reports> reportsList;
    private RecyclerViewClickListener recyclerViewClickListener;

    public ReportsDetailInfoRecyclerViewAdapter(List<Reports> list) {
        reportsList = new ArrayList<>();
        reportsList.addAll(list);
    }

    public void setRecyclerViewClickListener(RecyclerViewClickListener recyclerViewClickListener){
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @NonNull
    @Override
    public ReportsDetailInfoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reports_detail_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsDetailInfoRecyclerViewAdapter.ViewHolder holder, int position) {
        Reports reports = reportsList.get(position);
        holder.setIsRecyclable(false);
        if(reports.getInfoname() != null)
            holder.infoName.setText(reports.getInfoname());
        else
            holder.infoName.setText("");
        if(reports.getInfotext() != null)
            holder.infoText.setText(fromHtml(reports.getInfotext()));
        else
            holder.infoText.setText("");

    }

    @Override
    public int getItemCount() {
        return reportsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView infoName;
        private TextView infoText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            infoName = itemView.findViewById(R.id.info_name);
            infoText = itemView.findViewById(R.id.info_text);
            if(recyclerViewClickListener != null){
                itemView.setOnClickListener(v ->  recyclerViewClickListener.onClick(itemView, getAdapterPosition()));
            }
        }
    }
}
