package com.koreatech.naeilro.ui.restraunt.adapater;

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
import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantInfo;
import com.koreatech.naeilro.ui.main.MainActivity;

import java.util.List;

public class RestaurantImageRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantImageRecyclerViewAdapter.ViewHolder> {
    private NavController navController;
    private List<RestaurantInfo> restaurantInfoList;
    private RecyclerViewClickListener recyclerViewClickListener;
    private int selectIndex;
    private Context context;

    public RestaurantImageRecyclerViewAdapter(List<RestaurantInfo> restaurantInfoList, Context context) {
        this.restaurantInfoList = restaurantInfoList;
        this.context = context;
        selectIndex = 0;
        navController = Navigation.findNavController((MainActivity) context, R.id.nav_host_fragment);
    }
    private void selectImage(int index){
        selectIndex = index;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantImageRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_list_view, parent, false);
        return new RestaurantImageRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RestaurantInfo restaurant = restaurantInfoList.get(position);
        holder.setIsRecyclable(false);
        if (selectIndex == position)
            holder.selectImageView.setVisibility(View.VISIBLE);
        else
            holder.selectImageView.setVisibility(View.GONE);

        Glide.with(context)
                .load(restaurant.getOriginimgurl())
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
        return 0;
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
