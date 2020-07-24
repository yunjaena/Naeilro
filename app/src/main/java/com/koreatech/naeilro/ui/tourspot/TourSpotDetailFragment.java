package com.koreatech.naeilro.ui.tourspot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.koreatech.naeilro.R;

public class TourSpotDetailFragment extends Fragment {
    private int contentId;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tour_spot_detail, container, false);
        contentId = requireArguments().getInt("contentId");
        init(view);
        return view;
    }

    private void init(View view){

    }
}
