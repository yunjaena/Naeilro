package com.koreatech.naeilro.ui.house;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.koreatech.naeilro.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HouseIntroFragment extends Fragment {
    private View view;
    private Unbinder unbinder;
    @BindView(R.id.accomtaion_number)
    TextView accomtationNumber;
    @BindView(R.id.room_type)
    TextView roomType;
    @BindView(R.id.check_in)
    TextView checkIn;
    @BindView(R.id.check_out)
    TextView checkOut;
    @BindView(R.id.reservation_guide)
    TextView reservationGuide;
    @BindView(R.id.check_cook)
    TextView checkCooking;
    @BindView(R.id.parkinglot)
    TextView parkinglot;
    @BindView(R.id.food_place)
    TextView foodPlace;
    @BindView(R.id.sub_facility)
    TextView subFacility;
    @BindView(R.id.barbecue)
    TextView barbecue;
    @BindView(R.id.campfire)
    TextView campfire;
    @BindView(R.id.pick_up)
    TextView pickUpService;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_house_intro, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments().getString("accomtaion") != null) {
            accomtationNumber.setText("   " + getArguments().getString("accomtaion"));
        }
        if(getArguments().getString("roomType") != null) {
            roomType.setText("   " + getArguments().getString("roomType"));
        }
        if(getArguments().getString("checkIn") != null) {
            checkIn.setText("   " + getArguments().getString("checkIn"));
        }
        if(getArguments().getString("checkOut") != null) {
            checkOut.setText("   " + getArguments().getString("checkOut"));
        }
        if(getArguments().getString("reservationGuide") != null) {
            reservationGuide.setText("   " + getArguments().getString("reservationGuide"));
        }
        if(getArguments().getString("checkCooking") != null) {
            checkCooking.setText("   " + getArguments().getString("checkCooking"));
        }
        if(getArguments().getString("parkinglot") != null) {
            parkinglot.setText("   " + getArguments().getString("parkinglot"));
        }
        if(getArguments().getString("foodPlace") != null) {
            foodPlace.setText("   " + getArguments().getString("foodPlace"));
        }
        if(getArguments().getString("subFacility") != null) {
            subFacility.setText("   " + getArguments().getString("subFacility"));
        }
        if(getArguments().getString("barbecue") != null) {
            barbecue.setText("   " + getArguments().getString("barbecue"));
        }
        if(getArguments().getString("campfire") != null) {
            campfire.setText("   " + getArguments().getString("campfire"));
        }
        if(getArguments().getString("pickUp") != null) {
            pickUpService.setText("   " + getArguments().getString("pickUp"));
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }
}
