package com.koreatech.naeilro.ui.house;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.house.HouseInfo;
import com.koreatech.naeilro.network.interactor.HouseRestInteractor;
import com.koreatech.naeilro.ui.house.adapter.HouseDetailViewPagerAdapter;
import com.koreatech.naeilro.ui.house.presenter.HouseDetailFragmentPresenter;
import com.koreatech.naeilro.ui.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HouseDetailFragment extends Fragment implements HouseDetailFragmentContract.View {
    private View view;
    private HouseDetailFragmentPresenter houseDetailFragmentPresenter;
    private Unbinder unbinder;
    private TabLayout tabLayout;
    private NavController navController;
    private HouseDetailViewPagerAdapter houseDetailViewPagerAdapter;
    private ViewPager viewPager;
    private HouseCommonInfoFragment houseCommonInfoFragment;
    private HouseIntroFragment houseIntroFragment;
    private HouseImageFragment houseImageFragment;
    private String tel;
    private String title;
    private int contentTypeId;
    private int contentId;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_house_detail, container, false);
        this.unbinder = ButterKnife.bind(this, view);

        init(view);
        return view;
    }



    public void init(View view){
        houseDetailFragmentPresenter = new HouseDetailFragmentPresenter(new HouseRestInteractor(), this);
        contentTypeId = getArguments().getInt("contentTypeId");
        contentId = getArguments().getInt("contentId");
        title = getArguments().getString("title");
        tel = getArguments().getString("tel");
        houseDetailFragmentPresenter.getHouseCommonInfo(contentTypeId, contentId);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        houseDetailViewPagerAdapter = new HouseDetailViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);

        viewPager.setAdapter(houseDetailViewPagerAdapter);



        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }


    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).showProgressDialog(R.string.loading_house_info);
    }

    @Override
    public void hideLoading() {
        ((MainActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void showHouseCommonInfo(HouseInfo houseInfo) {
        houseCommonInfoFragment = new HouseCommonInfoFragment();
        Bundle commonInfoBundle = new Bundle();
        commonInfoBundle.putString("title", title);
        commonInfoBundle.putString("tel", tel);
        commonInfoBundle.putString("overview", houseInfo.getOverview());
        commonInfoBundle.putString("address", houseInfo.getAddr1());
        commonInfoBundle.putString("image", houseInfo.getFirstimage());
        houseCommonInfoFragment.setArguments(commonInfoBundle);
        houseDetailViewPagerAdapter.addFragment(houseCommonInfoFragment, "공통정보");
        houseDetailFragmentPresenter.getHouseIntroInfo(contentTypeId, contentId);
    }

    @Override
    public void showHouseIntroInfo(HouseInfo houseInfo) {
        houseIntroFragment = new HouseIntroFragment();
        Bundle commonInfoBundle = new Bundle();
        commonInfoBundle.putString("title", title);
        commonInfoBundle.putString("tel", tel);
        commonInfoBundle.putString("accomtaion", houseInfo.getAccomcountlodging());
        commonInfoBundle.putString("roomType", houseInfo.getRoomtype());
        commonInfoBundle.putString("checkIn", houseInfo.getCheckintime());
        commonInfoBundle.putString("checkOut", houseInfo.getCheckouttime());
        commonInfoBundle.putString("reservationGuide", houseInfo.getReservationlodging());
        commonInfoBundle.putString("checkCooking", houseInfo.getChkcooking());
        commonInfoBundle.putString("parkinglot", houseInfo.getParkinglodging());
        commonInfoBundle.putString("foodPlace", houseInfo.getFoodplace());
        commonInfoBundle.putString("subFacility", houseInfo.getSubfacility());
        commonInfoBundle.putString("barbecue", houseInfo.getBarbecue());
        commonInfoBundle.putString("campfire", houseInfo.getCampfire());
        commonInfoBundle.putString("pickUp", houseInfo.getPickup());
        houseIntroFragment.setArguments(commonInfoBundle);
        houseDetailViewPagerAdapter.addFragment(houseIntroFragment, "소개정보");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }


    @Override
    public void setPresenter(HouseDetailFragmentPresenter presenter) {
        this.houseDetailFragmentPresenter = presenter;
    }

}
