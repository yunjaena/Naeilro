package com.koreatech.naeilro.ui.myplan.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.myplan.RecommendPath;

import java.util.List;

public class MyPlanPathAdapter extends RecyclerView.Adapter<MyPlanPathAdapter.ViewHolder> {
    private List<RecommendPath> recommendPathList;
    private RecyclerViewClickListener recyclerViewClickListener;
    private Context context;


    public MyPlanPathAdapter(List<RecommendPath> recommendPathList, Context context) {
        this.recommendPathList = recommendPathList;
        this.context = context;
    }

    public void setRecyclerViewClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_plan_path, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0 && recommendPathList.size() != 1) {
            holder.trainLineImageView.post(() -> {
                int height = holder.trainLineImageView.getMeasuredHeight();
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(dpToPx(2), LinearLayout.LayoutParams.MATCH_PARENT);
                lp.gravity = Gravity.CENTER | Gravity.BOTTOM;
                lp.height = height / 2 - dpToPx(15);
                holder.trainLineImageView.setLayoutParams(lp);
            });
        } else if (position == recommendPathList.size() - 1 && recommendPathList.size() != 1) {
            holder.trainLineImageView.post(() -> {
                int height = holder.trainLineImageView.getMeasuredHeight();
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(dpToPx(2), LinearLayout.LayoutParams.MATCH_PARENT);
                lp.gravity = Gravity.CENTER | Gravity.TOP;
                lp.height = height / 2 - dpToPx(15);
                holder.trainLineImageView.setLayoutParams(lp);
            });
        }

        holder.trainStationTextView.setText(recommendPathList.get(position).getStationName());
        holder.trainStationPlaceTextView.setText(recommendPathList.get(position).getPlace());
        holder.selectPlaceLinearLayout.setOnClickListener(v -> {
            if (recyclerViewClickListener != null) {
                recyclerViewClickListener.onClick(holder.selectPlaceLinearLayout, position);
            }
        });
    }


    private int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


    @Override
    public int getItemCount() {
        return recommendPathList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView trainLineImageView;
        public ImageView trainIconImageView;
        public TextView trainStationTextView;
        public TextView trainStationPlaceTextView;
        public LinearLayout selectPlaceLinearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trainLineImageView = itemView.findViewById(R.id.train_line_image_view);
            trainIconImageView = itemView.findViewById(R.id.train_icon_image_view);
            trainStationTextView = itemView.findViewById(R.id.train_station_text_view);
            trainStationPlaceTextView = itemView.findViewById(R.id.train_station_place_text_view);
            selectPlaceLinearLayout = itemView.findViewById(R.id.show_select_place_linear_layout);
        }
    }
}
