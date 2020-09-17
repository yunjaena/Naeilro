package com.koreatech.naeilro.ui.myplan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.myplan.MyPlanNode;

import java.util.List;

public class MyPlanNodelAdapter extends RecyclerView.Adapter<MyPlanNodelAdapter.ViewHolder> {
    private List<MyPlanNode> myNodes;
    private Context context;
    private RecyclerViewClickListener recyclerViewClickListener;

    public MyPlanNodelAdapter(List<MyPlanNode> myNodes, Context context) {
        this.myNodes = myNodes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_node, parent, false);
        return new MyPlanNodelAdapter.ViewHolder(view);
    }
    public void setRecyclerViewClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyPlanNode myNode = myNodes.get(position);
        holder.myNodeName.setText(myNode.getTitle());
        Glide.with(context).load(myNode.getThumbnail())
                .thumbnail(0.05f)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_no_image)
                .into(holder.thumbnail);
        holder.cancelButton.setOnClickListener((view)-> recyclerViewClickListener.onClick(holder.cancelButton, position));
        holder.itemView.setOnClickListener(v->{
            if(recyclerViewClickListener != null){
                recyclerViewClickListener.onClick(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myNodes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView myNodeName;
        public ImageView thumbnail;
        public ImageButton cancelButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myNodeName = itemView.findViewById(R.id.node_title);
            thumbnail = itemView.findViewById(R.id.node_image_view);
            cancelButton = itemView.findViewById(R.id.node_cancel_button);
        }
    }
}
