package com.koreatech.naeilro.ui.train.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.koreatech.naeilro.R;
import com.koreatech.naeilro.ui.koreanindexer.KoreanIndexerRecyclerView;

import java.util.List;

public class TrainBottomSheetAdapter extends KoreanIndexerRecyclerView.KoreanIndexerRecyclerAdapter<TrainBottomSheetAdapter.ViewHolder> {
    private List<String> stringList;
    private Context context;

    public TrainBottomSheetAdapter(Context context, List<String> stringList) {
        this.stringList = stringList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_train_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textTextView.setText(stringList.get(position));
        if (position % 2 == 0) {
            holder.itemLinearLayout.setBackground(context.getResources().getDrawable(R.drawable.bg_white_border_right_down));
            GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) (holder.itemLinearLayout).getLayoutParams();
            layoutParams.setMargins(10, 0, 0, 0);
        } else {
            holder.itemLinearLayout.setBackground(context.getResources().getDrawable(R.drawable.bg_white_border_left_down));
            GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) (holder.itemLinearLayout).getLayoutParams();
            layoutParams.setMargins(0, 0, 10, 0);
        }
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTextView;
        private LinearLayout itemLinearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTextView = itemView.findViewById(R.id.text);
            itemLinearLayout = itemView.findViewById(R.id.item_linear_layout);
        }
    }
}
