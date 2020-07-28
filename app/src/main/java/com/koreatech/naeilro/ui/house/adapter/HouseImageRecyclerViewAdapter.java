package com.koreatech.naeilro.ui.house.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.entity.house.HouseInfo;

import java.util.List;

public class HouseImageRecyclerViewAdapter extends RecyclerView.Adapter<HouseImageRecyclerViewAdapter.ViewHolder> {
    @NonNull
    @Override
    public HouseImageRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_list_view, parent, false);
        return new HouseImageRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HouseInfo houseInfo = houseInfoList.get(position);
        holder.setIsRecyclable(false);

        if (selectIndex == position)
            holder.selectImageView.setVisibility(View.VISIBLE);
        else
            holder.selectImageView.setVisibility(View.GONE);

        Glide.with(context)
                .load(houseInfo.getOriginimgurl())
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
        return houseInfoList.size();
    }

    private List<HouseInfo> houseInfoList;
    private RecyclerViewClickListener recyclerViewClickListener;
    private int selectIndex;
    private Context context;

    public HouseImageRecyclerViewAdapter(List<HouseInfo> houseInfoList, Context context) {
        this.houseInfoList = houseInfoList;
        selectIndex = 0;
        this.context = context;
    }
    private void selectImage(int index) {
        selectIndex = index;
        notifyDataSetChanged();
    }
    public void setRecyclerViewClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
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
