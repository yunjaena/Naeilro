package com.koreatech.naeilro.ui.myplan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.myplan.MyPlanNode;

import java.util.List;

public class MyPlanDetailAdapter extends RecyclerView.Adapter<MyPlanDetailAdapter.ViewHolder>{
    private List<MyPlanNode> myNodes;
    private Context context;

    public MyPlanDetailAdapter(List<MyPlanNode> myNodes, Context context) {
        this.myNodes = myNodes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_house_info, parent, false);
        return new MyPlanDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyPlanNode myNode = myNodes.get(position);
        holder.myNodeName.setText(myNode.getTitle());
        Glide.with(context).load(myNode.getThumbnail()).error(R.drawable.ic_no_image).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return myNodes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView myNodeName;
        public ImageView thumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myNodeName = itemView.findViewById(R.id.house_title);
            thumbnail = itemView.findViewById(R.id.house_image_view);
        }
    }
}
