package com.koreatech.naeilro.ui.myplan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.myplan.MyPlan;

import java.util.List;

public class MyPlanAdapter extends RecyclerView.Adapter<MyPlanAdapter.ViewHolder> {
    private List<MyPlan> myPlans;
    private RecyclerViewClickListener recyclerViewClickListener;

    public MyPlanAdapter(List<MyPlan> myPlans) {
        this.myPlans = myPlans;
    }

    public void setRecyclerViewClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }
    public void clearRecyclerListItem(){
        myPlans.clear();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myplan_list, parent, false);
        return new MyPlanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyPlan myPlan = myPlans.get(position);
        holder.myPlanName.setText(myPlan.getPlanTitle());
        holder.deletePlanButton.setOnClickListener((view)-> recyclerViewClickListener.onClick(holder.deletePlanButton, position));
        holder.itemView.setOnClickListener(v -> {
            if (recyclerViewClickListener != null) {
                recyclerViewClickListener.onClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myPlans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView myPlanName;
        public ImageButton deletePlanButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myPlanName = itemView.findViewById(R.id.my_plan_name);
            deletePlanButton = itemView.findViewById(R.id.delete_plan_button);
        }
    }
}
