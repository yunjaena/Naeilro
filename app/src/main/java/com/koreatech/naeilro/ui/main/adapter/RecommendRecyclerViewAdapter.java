package com.koreatech.naeilro.ui.main.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.recommend.MyRecommendItem;
import com.koreatech.naeilro.network.entity.recommend.Recommend;

import java.util.List;

public class RecommendRecyclerViewAdapter extends RecyclerView.Adapter<RecommendRecyclerViewAdapter.ViewHolder>  {
    private List<MyRecommendItem> myRecommendItemList;
    private RecyclerViewClickListener recyclerViewClickListener;
    private Context context;

    public RecommendRecyclerViewAdapter(List<MyRecommendItem> myRecommendItemList, Context context) {
        this.myRecommendItemList = myRecommendItemList;
        this.context = context;
    }
    public void setRecyclerViewClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, parent, false);
        return new RecommendRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyRecommendItem myRecommendItem = myRecommendItemList.get(position);
        Glide.with(context).load(myRecommendItem.getThumbnail()).error(R.drawable.ic_no_image).into(holder.recommendThumbnail);
        String s = myRecommendItem.getTitle();
        holder.recommendTitle.setText(s);
        holder.itemView.setOnClickListener(v->{
            if(recyclerViewClickListener != null){
                recyclerViewClickListener.onClick(v,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myRecommendItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView recommendTitle;
        public ImageView recommendThumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recommendTitle = itemView.findViewById(R.id.recommend_title);
            recommendThumbnail = itemView.findViewById(R.id.recommend_thumbnail);
        }
    }
}
