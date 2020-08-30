package com.koreatech.naeilro.ui.myplan.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.myplan.MyPlan;

import java.util.List;

public class MyPlanListAdapter extends RecyclerView.Adapter<MyPlanListAdapter.ViewHolder> {
    private List<MyPlan> myPlans;
    private RecyclerViewClickListener recyclerViewClickListener;

    public MyPlanListAdapter(List<MyPlan> myPlans) {
        this.myPlans = myPlans;
    }

    public void setRecyclerViewClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myplan_list, parent, false);
        return new MyPlanListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyPlan myPlan = myPlans.get(position);
        holder.myPlanName.setText(myPlan.getPlanTitle());
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myPlanName = itemView.findViewById(R.id.my_plan_name);
        }
    }
}
