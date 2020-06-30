package com.koreatech.naeilro.ui.festival.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.event.Festival;

import java.util.ArrayList;
import java.util.List;

public class FestivalInfoRecyclerViewAdapter extends RecyclerView.Adapter<FestivalInfoRecyclerViewAdapter.ViewHolder> {
    private List<Festival> festivalList;

    public FestivalInfoRecyclerViewAdapter() {
        festivalList = new ArrayList<>();
    }

    @NonNull
    @Override
    public FestivalInfoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_house_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FestivalInfoRecyclerViewAdapter.ViewHolder holder, int position) {
        Festival festivalInfo = festivalList.get(position);
        holder.setIsRecyclable(false);
        if(festivalInfo.getTitle() != null)
            holder.festivalTitleTextView.setText(festivalInfo.getTitle());
        else
            holder.festivalTitleTextView.setText("행사명이 등록되지 않았습니다.");
        if(festivalInfo.getAddr1() != null)
            holder.festivalAddressTextView.setText(festivalInfo.getAddr1());
        else
            holder.festivalAddressTextView.setText("주소가 등록되지 않았습니다.");
        if(festivalInfo.getTel() != null)
            holder.festivalTelTextView.setText(festivalInfo.getTel());
        else
            holder.festivalTelTextView.setText("전화번호가 등록되지 않았습니다.");
        /*
        if(festivalInfo.getHanok() == 1)
            holder.festivalColorTextView.setVisibility(View.VISIBLE);
        else
            holder.houseHanokTextView.setVisibility(View.INVISIBLE);

         */
        if(festivalInfo.getFirstimage() != null){
            Glide.with(holder.festivalImageView)
                    .load(festivalInfo.getFirstimage())
                    .into(holder.festivalImageView);
        }else
            Glide.with(holder.festivalImageView)
                    .load(R.drawable.ic_no_image)
                    .into(holder.festivalImageView);

    }
    public void addItem(List<Festival> item){
        for(int i=0;i<item.size();i++){
            festivalList.add(item.get(i));
        }
        notifyDataSetChanged();
    }
    public void clearItem(){
        festivalList.clear();
    }

    @Override
    public int getItemCount() {
        return festivalList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView festivalTitleTextView;
        public TextView festivalAddressTextView;
        public TextView festivalTelTextView;
        public TextView festivalColorTextView;
        public ImageView festivalImageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            festivalTitleTextView = itemView.findViewById(R.id.house_title);
            festivalAddressTextView = itemView.findViewById(R.id.house_address);
            festivalTelTextView = itemView.findViewById(R.id.house_tel);
            festivalColorTextView = itemView.findViewById(R.id.hanok_text);
            festivalImageView = itemView.findViewById(R.id.house_image_view);
        }
    }
}
