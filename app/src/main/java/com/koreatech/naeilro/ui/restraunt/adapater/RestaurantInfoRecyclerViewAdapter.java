package com.koreatech.naeilro.ui.restraunt.adapater;

import android.content.Context;
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
import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantInfo;
import com.koreatech.naeilro.ui.main.MainActivity;

import java.util.List;

public class RestaurantInfoRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantInfoRecyclerViewAdapter.ViewHolder> {
    NavController navController;
    private List<RestaurantInfo> restaurantInfoList;
    private Context context;
    private RecyclerViewClickListener recyclerViewClickListener;

    public RestaurantInfoRecyclerViewAdapter(List<RestaurantInfo> restaurantInfoList, Context context) {
        this.context = context;
        this.restaurantInfoList = restaurantInfoList;
        navController = Navigation.findNavController((MainActivity) context, R.id.nav_host_fragment);
    }

    public void setRecyclerClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @NonNull
    @Override
    public RestaurantInfoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantInfoRecyclerViewAdapter.ViewHolder holder, int position) {
        RestaurantInfo restaurantInfo = restaurantInfoList.get(position);
        holder.setIsRecyclable(false);
        if (restaurantInfo.getTitle() != null)
            holder.restaurantTitleTextView.setText(restaurantInfo.getTitle());
        else
            holder.restaurantTitleTextView.setText("식당명이 등록되지 않았습니다.");
        if (restaurantInfo.getAddress() != null)
            holder.restaurantAddressTextView.setText(restaurantInfo.getAddress());
        else
            holder.restaurantAddressTextView.setText("주소가 등록되지 않았습니다.");
        if (restaurantInfo.getTelephoneNumber() != null)
            holder.restaurantTelTextView.setText(restaurantInfo.getTelephoneNumber());
        else
            holder.restaurantTelTextView.setText("전화번호가 등록되지 않았습니다.");

        if (restaurantInfo.getFirstImage() != null) {
            Glide.with(holder.restaurantImageView)
                    .load(restaurantInfo.getFirstImage())
                    .into(holder.restaurantImageView);
        } else
            Glide.with(holder.restaurantImageView)
                    .load(R.drawable.ic_no_image)
                    .into(holder.restaurantImageView);
    }

    @Override
    public int getItemCount() {
        return restaurantInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public TextView restaurantTitleTextView;
        public TextView restaurantAddressTextView;
        public TextView restaurantTelTextView;
        public ImageView restaurantImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            view.setOnClickListener(v -> {
                if (recyclerViewClickListener != null)
                    recyclerViewClickListener.onClick(view, getAdapterPosition());
            });
            restaurantTitleTextView = itemView.findViewById(R.id.restaurant_title);
            restaurantAddressTextView = itemView.findViewById(R.id.restaurant_address);
            restaurantTelTextView = itemView.findViewById(R.id.restaurant_tel);
            restaurantImageView = itemView.findViewById(R.id.restaurant_image_view);
        }

    }
}
