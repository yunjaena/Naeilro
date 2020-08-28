package com.koreatech.naeilro.ui.myplan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyPlanCollectionAdapter extends RecyclerView.Adapter<MyPlanCollectionAdapter.ViewHolder> {
    private List<String> collectionList;
    private Set<Integer> selectedPosition;

    public MyPlanCollectionAdapter(List<String> collectionList) {
        this.collectionList = collectionList;
        selectedPosition = new HashSet<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_plan_bottom_sheet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.collectionTextView.setText(collectionList.get(position));
        if (selectedPosition.contains(position)) {
            holder.collectionRadioButton.setChecked(true);
        } else {
            holder.collectionRadioButton.setChecked(false);
        }
    }


    @Override
    public int getItemCount() {
        return collectionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout collectionLinearLayout;
        public TextView collectionTextView;
        public RadioButton collectionRadioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.setIsRecyclable(false);
            collectionLinearLayout = itemView.findViewById(R.id.my_plan_bottom_linear_layout);
            collectionTextView = itemView.findViewById(R.id.my_plan_bottom_text_view);
            collectionRadioButton = itemView.findViewById(R.id.my_plan_bottom_radio_button);
            collectionRadioButton.setClickable(false);
            collectionLinearLayout.setOnClickListener(v -> inverseRadioButtonChecked(collectionRadioButton, getAdapterPosition()));
        }


        private void inverseRadioButtonChecked(RadioButton radioButton, int position) {
            if (selectedPosition.contains(position)) {
                radioButton.setChecked(false);
                selectedPosition.remove(position);
            } else {
                radioButton.setChecked(true);
                selectedPosition.add(position);
            }


        }
    }
}
