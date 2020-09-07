package com.koreatech.naeilro.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.recommend.MyRecommendItem;
import com.koreatech.naeilro.network.interactor.RecommendRestInteractor;
import com.koreatech.naeilro.ui.main.adapter.RecommendRecyclerViewAdapter;
import com.koreatech.naeilro.ui.main.presenter.HomePresenter;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment implements HomeFragmentContract.View {
    @BindView(R.id.category_tourspot)
    LinearLayout categoryTourspotLayout;
    @BindView(R.id.category_facility)
    LinearLayout categoryFacilityLayout;
    @BindView(R.id.category_festival)
    LinearLayout categoryFestivalLayout;
    @BindView(R.id.category_house)
    LinearLayout categoryHouseLayout;
    @BindView(R.id.category_reports)
    LinearLayout categoryReportsLayout;
    @BindView(R.id.category_restraunt)
    LinearLayout categoryRestrauntLayout;
    @BindView(R.id.category_train)
    LinearLayout categoryTrainLayout;
    @BindView(R.id.category_weather)
    LinearLayout categoryWeatherLayout;
    private RecyclerView recommendRecyclerView;
    private RecommendRecyclerViewAdapter recommendRecyclerViewAdapter;
    private HomePresenter homePresenter;
    private List<MyRecommendItem> recommendItemList = new LinkedList<>();

    private View root;
    private NavController navController;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        homePresenter = new HomePresenter(new RecommendRestInteractor(), this);
        recommendRecyclerView = root.findViewById(R.id.recommend_recyclerview);
        homePresenter.getRecommendList();
        return root;
    }
    @OnClick(R.id.category_tourspot)
    void goToTourSpotFragment(){
        navController.navigate(R.id.action_navigation_home_to_navigation_tourspot);
    }
    @OnClick(R.id.category_train)
    void goToTrainInfoFragment(){
        navController.navigate(R.id.action_navigation_home_to_navigation_train);
    }
    @OnClick(R.id.category_restraunt)
    void goToRestrauntFragment(){
        navController.navigate(R.id.action_navigation_home_to_navigation_restraunt);
    }

    @OnClick(R.id.category_house)
    void goToHouseFragment(){
        navController.navigate(R.id.action_navigation_home_to_navigation_house);
    }
    @OnClick(R.id.category_reports)
    void goToReportsFragment(){
        navController.navigate(R.id.action_navigation_home_to_navigation_reports);
    }
    @OnClick(R.id.category_facility)
    void goToFacilityFragment(){
        navController.navigate(R.id.action_navigation_home_to_navigation_facility);
    }
    @OnClick(R.id.category_festival)
    void goToFestivalFragment(){
        navController.navigate(R.id.action_navigation_home_to_navigation_festival);
    }
    @OnClick(R.id.category_weather)
    void goToWeatherFragment(){
        navController.navigate(R.id.action_navigation_home_to_navigation_weather);
    }

    public void initRecyclerView(List<MyRecommendItem> list){
        recommendRecyclerViewAdapter = new RecommendRecyclerViewAdapter(list,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recommendRecyclerView.setLayoutManager(linearLayoutManager);
        recommendRecyclerView.setAdapter(recommendRecyclerViewAdapter);
        recommendRecyclerViewAdapter.setRecyclerViewClickListener(new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                String contentTypeId = recommendItemList.get(position).getContentType();
                int contentId = Integer.parseInt(recommendItemList.get(position).getContendID());
                Bundle bundle = new Bundle();
                bundle.putString("title", recommendItemList.get(position).getTitle());
                bundle.putInt("contentId", contentId);
                if(contentTypeId.equals("12")){//관광지
                    navController.navigate(R.id.action_navigation_home_to_navigation_tourspot_detail, bundle);
                }
                else if(contentTypeId.equals("14")){//문화시설
                    navController.navigate(R.id.action_navigation_home_to_navigation_detail_facility, bundle);
                }
                else if(contentTypeId.equals("15")){//축제/행사
                    navController.navigate(R.id.action_navigation_home_to_navigation_detail_festival, bundle);
                }
                else if(contentTypeId.equals("28")){//레포츠
                    navController.navigate(R.id.action_navigation_home_to_navigation_detail_reports, bundle);
                }
                else if(contentTypeId.equals("32")){//숙박
                    navController.navigate(R.id.action_navigation_home_to_navigation_house_detail, bundle);
                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        recommendRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showRecommendList(List<MyRecommendItem> recommendList) {
        recommendItemList.clear();
        recommendItemList.addAll(recommendList);
        initRecyclerView(recommendItemList);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showFailure(String s) {
        ToastUtil.getInstance().makeShort(s);
    }

    @Override
    public void setPresenter(HomePresenter presenter) {

    }
}
