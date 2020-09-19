package com.koreatech.naeilro.ui.search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.search.SearchInfo;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<SearchInfo> searchInfoList;
    private RecyclerViewClickListener recyclerViewClickListener;

    public SearchAdapter(List<SearchInfo> searchInfoList) {
        this.searchInfoList = searchInfoList;
    }

    public void setRecyclerViewClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchInfo searchInfo = searchInfoList.get(position);
        if (searchInfo.getTitle() != null)
            holder.searchTitleTextView.setText(searchInfo.getTitle());
        if (searchInfo.getAddress() != null)
            holder.searchAddressTextView.setText(searchInfo.getAddress());
        else
            holder.searchAddressTextView.setText(R.string.address_no_info);

        if (searchInfo.getTelephoneNumber() != null)
            holder.searchTelephoneTextView.setText(searchInfo.getTelephoneNumber());
        else
            holder.searchTelephoneTextView.setText(R.string.phone_number_no_info);

        holder.searchItemLinearLayout.setOnClickListener(v -> {
            if (recyclerViewClickListener != null)
                recyclerViewClickListener.onClick(holder.searchItemLinearLayout, position);
        });

        if (searchInfo.getFirstImage() != null)
            Glide.with(holder.searchImageView)
                    .load(searchInfo.getFirstImage())
                    .thumbnail(0.05f)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .error(R.drawable.ic_no_image)
                    .into(holder.searchImageView);
        else
            Glide.with(holder.searchImageView).load(R.drawable.ic_no_image).into(holder.searchImageView);

    }

    @Override
    public int getItemCount() {
        return searchInfoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView searchImageView;
        public TextView searchTitleTextView;
        public TextView searchAddressTextView;
        public TextView searchTelephoneTextView;
        public LinearLayout searchItemLinearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            searchImageView = itemView.findViewById(R.id.search_image_view);
            searchTitleTextView = itemView.findViewById(R.id.search_title);
            searchAddressTextView = itemView.findViewById(R.id.search_address);
            searchTelephoneTextView = itemView.findViewById(R.id.search_tel);
            searchItemLinearLayout = itemView.findViewById(R.id.search_item_linear_layout);
        }
    }
}
