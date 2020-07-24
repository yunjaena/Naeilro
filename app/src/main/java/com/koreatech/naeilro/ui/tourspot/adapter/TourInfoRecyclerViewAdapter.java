package com.koreatech.naeilro.ui.tourspot.adapter;

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
import com.koreatech.naeilro.network.entity.tour.TourInfo;
import com.koreatech.naeilro.ui.main.MainActivity;

import java.util.List;

public class TourInfoRecyclerViewAdapter extends RecyclerView.Adapter<TourInfoRecyclerViewAdapter.ViewHolder> {
    NavController navController;
    private List<TourInfo> tourInfoList;
    private RecyclerViewClickListener recyclerViewClickListener;
    private Context context;

    public TourInfoRecyclerViewAdapter(Context context, List<TourInfo> tourInfoList) {
        this.context = context;
        this.tourInfoList = tourInfoList;
        navController = Navigation.findNavController((MainActivity) context, R.id.nav_host_fragment);
    }

    @NonNull
    @Override
    public TourInfoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour_info, parent, false);
        return new ViewHolder(view);
    }

    public void setRecyclerViewClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull TourInfoRecyclerViewAdapter.ViewHolder holder, int position) {
        TourInfo tourInfo = tourInfoList.get(position);
        holder.setIsRecyclable(false);
        if (tourInfo.getTitle() != null)
            holder.tourTitleTextView.setText(tourInfo.getTitle());
        else
            holder.tourTitleTextView.setText("여행 정보가 등록되지 않았습니다.");
        if (tourInfo.getAddress() != null)
            holder.tourAddressTextView.setText(tourInfo.getAddress());
        else
            holder.tourAddressTextView.setText("주소가 등록되지 않았습니다.");
        if (tourInfo.getTelephoneNumber() != null)
            holder.tourTelTextView.setText(tourInfo.getTelephoneNumber());
        else
            holder.tourTelTextView.setText("전화번호가 등록되지 않았습니다.");

        if (tourInfo.getFirstImage() != null) {
            Glide.with(holder.tourImageView)
                    .load(tourInfo.getFirstImage())
                    .into(holder.tourImageView);
        } else
            Glide.with(holder.tourImageView)
                    .load(R.drawable.ic_no_image)
                    .into(holder.tourImageView);

        holder.view.setOnClickListener(v -> {
            if (recyclerViewClickListener != null)
                recyclerViewClickListener.onClick(v, position);
        });


    }

    @Override
    public int getItemCount() {
        return tourInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public TextView tourTitleTextView;
        public TextView tourAddressTextView;
        public TextView tourTelTextView;
        public TextView tourHanokTextView;
        public ImageView tourImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tourTitleTextView = itemView.findViewById(R.id.tour_title);
            tourAddressTextView = itemView.findViewById(R.id.tour_address);
            tourTelTextView = itemView.findViewById(R.id.tour_tel);
            tourHanokTextView = itemView.findViewById(R.id.hanok_text);
            tourImageView = itemView.findViewById(R.id.tour_image_view);
        }

    }
}
