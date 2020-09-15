package com.koreatech.naeilro.ui.house;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.koreatech.naeilro.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HouseCommonInfoFragment extends Fragment {
    private View view;
    private Unbinder unbinder;
    @BindView(R.id.house_detail_title)
    TextView houseTitle;

    @BindView(R.id.house_detail_tel)
    TextView houseTel;
    @BindView(R.id.house_detail_address)
    TextView houseAddress;
    @BindView(R.id.house_detail_overview)
    TextView houseOverview;
    @BindView(R.id.house_detail_image)
    ImageView houseImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_house_common_info, container, false);
        this.unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        houseTitle.setText(getArguments().getString("title"));
        houseTel.setText("전화번호 : " + getArguments().getString("tel"));
        houseAddress.setText("주소 : " + getArguments().getString("address"));

        String overview = getArguments().getString("overview");
        houseOverview.setText("소개 : " + setFilter(overview));
        String imageUrl = getArguments().getString("image");
        if (imageUrl == null) {
            Glide.with(houseImage).load(R.drawable.ic_no_image).into(houseImage);
        } else {
            Glide.with(houseImage).load(getArguments().getString("image")).into(houseImage);
        }


    }

    public String setFilter(String overview) {
        String arrStr[] = overview.split("");
        String filteredString = "";
        boolean flag = false;
        String[] filter = {"<", ">", "=", "+", "?", "^", "$", "@", "*", "※", "/", "\\"};
        for (int i = 0; i < arrStr.length; i++) {
            if (arrStr[i].equals("<")) {
                flag = true;
            }
            if (arrStr[i].equals(">")) {
                flag = false;
                continue;
            }
            if(flag == false){
                filteredString = filteredString.concat(arrStr[i]);
            }
        }
        return  filteredString;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }

}
