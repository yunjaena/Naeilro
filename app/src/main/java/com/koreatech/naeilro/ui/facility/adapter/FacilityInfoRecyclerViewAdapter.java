package com.koreatech.naeilro.ui.facility.adapter;

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
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class FacilityInfoRecyclerViewAdapter extends RecyclerView.Adapter<FacilityInfoRecyclerViewAdapter.ViewHolder> {
    private List<Facility> facilityList;
    NavController navController;
    Context context;

    @NonNull
    @Override
    public FacilityInfoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_house_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacilityInfoRecyclerViewAdapter.ViewHolder holder, int position) {
        Facility facility = facilityList.get(position);
        holder.setIsRecyclable(false);
        if(facility.getTitle() != null)
            holder.facilityTitleTextView.setText(facility.getTitle());
        else
            holder.facilityTitleTextView.setText("문화시설이 등록되지 않았습니다.");
        if(facility.getAddr1() != null)
            holder.facilityAddressTextView.setText(facility.getAddr1());
        else
            holder.facilityAddressTextView.setText("");
        if(facility.getTitle() != null)
            holder.facilityTelTextView.setText(facility.getTel());
        else
            holder.facilityTelTextView.setText("");
        if(facility.getFirstimage() != null)
            Glide.with(holder.facilityImageView).load(facility.getFirstimage()).into(holder.facilityImageView);
        else
            Glide.with(holder.facilityImageView).load(R.drawable.ic_no_image).into(holder.facilityImageView);

        holder.view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("contentId", Integer.parseInt(facility.getContentid()));
                bundle.putString("title", facility.getTitle());
                navController.navigate(R.id.action_navigation_facility_to_navigation_detail_facility, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return facilityList.size();
    }
    public void addItem(List<Facility> item){
        for(int i=0;i<item.size();i++){
            facilityList.add(item.get(i));
        }
        notifyDataSetChanged();
    }
    public void clearItem(){
        facilityList.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView facilityTitleTextView;
        public TextView facilityAddressTextView;
        public TextView facilityTelTextView;
        public ImageView facilityImageView;
        public final View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            facilityTitleTextView = itemView.findViewById(R.id.house_title);
            facilityAddressTextView = itemView.findViewById(R.id.house_address);
            facilityTelTextView = itemView.findViewById(R.id.house_tel);
            facilityImageView = itemView.findViewById(R.id.house_image_view);
        }
    }
    public FacilityInfoRecyclerViewAdapter(Context context) {
        this.context = context;
        facilityList = new ArrayList<>();
        navController = Navigation.findNavController((MainActivity)context, R.id.nav_host_fragment);

    }
}
