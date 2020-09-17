package com.koreatech.naeilro.ui.house.adapter;

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
import com.koreatech.naeilro.network.entity.house.HouseInfo;
import com.koreatech.naeilro.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class HouseInfoRecyclerViewAdapter extends RecyclerView.Adapter<HouseInfoRecyclerViewAdapter.ViewHolder> {
    NavController navController;
    private List<HouseInfo> houseInfoList;
    private Context context;

    public HouseInfoRecyclerViewAdapter(Context context) {
        this.context = context;
        houseInfoList = new ArrayList<>();
        navController = Navigation.findNavController((MainActivity) context, R.id.nav_host_fragment);
    }

    @NonNull
    @Override
    public HouseInfoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_house_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HouseInfoRecyclerViewAdapter.ViewHolder holder, int position) {
        HouseInfo houseInfo = houseInfoList.get(position);
        holder.setIsRecyclable(false);
        if (houseInfo.getTitle() != null)
            holder.houseTitleTextView.setText(houseInfo.getTitle());
        else
            holder.houseTitleTextView.setText("숙소명이 등록되지 않았습니다.");
        if (houseInfo.getAddr1() != null)
            holder.houseAddressTextView.setText(houseInfo.getAddr1());
        else
            holder.houseAddressTextView.setText("주소가 등록되지 않았습니다.");
        if (houseInfo.getTel() != null)
            holder.houseTelTextView.setText(houseInfo.getTel());
        else
            holder.houseTelTextView.setText("");
        if (houseInfo.getHanok() == 1)
            holder.houseHanokTextView.setVisibility(View.VISIBLE);
        else
            holder.houseHanokTextView.setVisibility(View.INVISIBLE);
        if (houseInfo.getFirstimage() != null) {
            Glide.with(holder.houseImageView)
                    .load(houseInfo.getFirstimage())
                    .thumbnail(0.05f)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(holder.houseImageView);
        } else
            Glide.with(holder.houseImageView)
                    .load(R.drawable.ic_no_image)
                    .into(holder.houseImageView);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("contentTypeId", Integer.parseInt(houseInfo.getContenttypeid()));
                bundle.putInt("contentId", Integer.parseInt(houseInfo.getContentid()));
                bundle.putString("title", houseInfo.getTitle());
                bundle.putString("tel", houseInfo.getTel());
                navController.navigate(R.id.action_navigation_house_to_navigation_house_detail, bundle);
            }
        });


    }

    public void addItem(List<HouseInfo> item) {
        for (int i = 0; i < item.size(); i++) {
            houseInfoList.add(item.get(i));
        }
        notifyDataSetChanged();
    }

    public void clearItem() {
        houseInfoList.clear();
    }

    @Override
    public int getItemCount() {
        return houseInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public TextView houseTitleTextView;
        public TextView houseAddressTextView;
        public TextView houseTelTextView;
        public TextView houseHanokTextView;
        public ImageView houseImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            houseTitleTextView = itemView.findViewById(R.id.house_title);
            houseAddressTextView = itemView.findViewById(R.id.house_address);
            houseTelTextView = itemView.findViewById(R.id.house_tel);
            houseHanokTextView = itemView.findViewById(R.id.hanok_text);
            houseImageView = itemView.findViewById(R.id.house_image_view);
        }

    }
}
