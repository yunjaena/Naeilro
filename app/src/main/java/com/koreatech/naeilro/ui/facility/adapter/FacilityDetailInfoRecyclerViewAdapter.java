package com.koreatech.naeilro.ui.facility.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.entity.reports.Reports;
import com.koreatech.naeilro.ui.reports.adapter.ReportsDetailInfoRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.koreatech.naeilro.ui.reports.ReportsDetailFragment.fromHtml;

public class FacilityDetailInfoRecyclerViewAdapter extends RecyclerView.Adapter<FacilityDetailInfoRecyclerViewAdapter.ViewHolder> {
    private List<Facility> facilityList;

    public FacilityDetailInfoRecyclerViewAdapter(List<Facility> list) {
        facilityList = new ArrayList<>();
        facilityList.addAll(list);
    }

    @NonNull
    @Override
    public FacilityDetailInfoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reports_detail_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Facility facility = facilityList.get(position);
        holder.setIsRecyclable(false);
        if(facility.getInfoname() != null)
            holder.infoName.setText(facility.getInfoname());
        else
            holder.infoName.setText("");
        if(facility.getInfotext() != null)
            holder.infoText.setText(fromHtml(facility.getInfotext()));
        else
            holder.infoText.setText("");
    }

    @Override
    public int getItemCount() {
        return facilityList.size();
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
