package com.koreatech.naeilro.ui.facility.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.entity.reports.Reports;
import com.koreatech.naeilro.network.entity.tour.TourInfo;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.reports.adapter.ReportsImageRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class FacilityImageRecyclerViewAdapter extends RecyclerView.Adapter<FacilityImageRecyclerViewAdapter.ViewHolder>{
    NavController navController;
    private List<Facility> facilityInfoList;
    private RecyclerViewClickListener recyclerViewClickListener;
    private int selectIndex;
    private Context context;

    public FacilityImageRecyclerViewAdapter(List<Facility> list, Context context) {
        this.context = context;
        this.facilityInfoList = list;
        selectIndex = 0;
        navController = Navigation.findNavController((MainActivity) context, R.id.nav_host_fragment);
    }
    private void selectImage(int index) {
        selectIndex = index;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FacilityImageRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_list_view, parent, false);
        return new FacilityImageRecyclerViewAdapter.ViewHolder(view);
    }
    public void setRecyclerViewClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Facility facility = facilityInfoList.get(position);
        holder.setIsRecyclable(false);

        if (selectIndex == position)
            holder.selectImageView.setVisibility(View.VISIBLE);
        else
            holder.selectImageView.setVisibility(View.GONE);

        Glide.with(context)
                .load(facility.getOriginimgurl())
                .thumbnail(0.05f)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_no_image)
                .into(holder.showImageView);


        holder.view.setOnClickListener(v -> {
            selectImage(position);
            if (recyclerViewClickListener != null)
                recyclerViewClickListener.onClick(v, position);
        });

    }

    @Override
    public int getItemCount() {
        return facilityInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final View view;
        public ImageView selectImageView;
        public ImageView showImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            selectImageView = itemView.findViewById(R.id.select_image_view);
            showImageView = itemView.findViewById(R.id.show_image_view);
        }
    }
}
