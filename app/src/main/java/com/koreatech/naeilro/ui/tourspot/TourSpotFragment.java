package com.koreatech.naeilro.ui.tourspot;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.tour.TourInfo;
import com.koreatech.naeilro.network.interactor.TourRestInteractor;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.tourspot.adapter.TourInfoRecyclerViewAdapter;
import com.koreatech.naeilro.ui.tourspot.presenter.TourSpotContract;
import com.koreatech.naeilro.ui.tourspot.presenter.TourSpotPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;


public class TourSpotFragment extends Fragment implements TourSpotContract.View {
    public final int PAGE_MAX_LOAD_NUMBER = 20;
    @BindView(R.id.city_spinner)
    Spinner citySpinner;
    @BindView(R.id.detail_city_spinner)
    Spinner detailCitySpinner;
    @BindView(R.id.search_tour)
    Button searchButton;
    @BindView(R.id.tour_info_recycler_view)
    RecyclerView tourInfoRecyclerView;

    private LinearLayoutManager linearLayoutManager;
    private TourInfoRecyclerViewAdapter tourInfoRecyclerViewAdapter;
    private int pageNumber;
    private int areaCode;
    private int sigunguCode;
    private Unbinder unbinder;
    private List<TourInfo> tourInfoList;
    private TourSpotPresenter tourSpotPresenter;
    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tour_spot, container, false);
        unbinder = ButterKnife.bind(this, view);
        init(view);
        Log.e("sdfg", "onCreateView");
        return view;
    }

    private void init(View view) {
        tourInfoList = new ArrayList<>();
        pageNumber = 1;
        navController = Navigation.findNavController((MainActivity)getContext(), R.id.nav_host_fragment);
        tourSpotPresenter = new TourSpotPresenter(this, new TourRestInteractor());
        initRecyclerView(view);
        tourSpotPresenter.getTourInfoList(pageNumber, PAGE_MAX_LOAD_NUMBER);
    }

    private void initRecyclerView(View view) {
        tourInfoRecyclerViewAdapter = new TourInfoRecyclerViewAdapter(getContext(), tourInfoList);
        linearLayoutManager = new LinearLayoutManager(getContext());
        tourInfoRecyclerView.setLayoutManager(linearLayoutManager);
        tourInfoRecyclerView.setAdapter(tourInfoRecyclerViewAdapter);
        tourInfoRecyclerViewAdapter.setRecyclerViewClickListener(new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("contentId", Integer.parseInt(tourInfoList.get(position).getContentid()));
                navController.navigate(R.id.action_navigation_tourspot_to_navigation_tourspot_detail, bundle);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        tourInfoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int lastVisible = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                if (lastVisible >= pageNumber * PAGE_MAX_LOAD_NUMBER - 1) {
                    pageNumber++;
                    getTourInfo();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        tourInfoRecyclerViewAdapter.notifyDataSetChanged();
    }


    public void getTourInfo() {
        Region region = Region.getRegionOrDefault(citySpinner.getSelectedItemPosition());
        int areaSpecificCode = sigunguCode;

        if (region.getAreaCode() == areaCode && region.getSigunguDifferenceIndex() < sigunguCode)
            areaSpecificCode += region.getSigunguCodeDifference();
        
        if (areaCode == 0)
            tourSpotPresenter.getTourInfoList(pageNumber, PAGE_MAX_LOAD_NUMBER);
        else
            tourSpotPresenter.getTourInfoList(pageNumber, PAGE_MAX_LOAD_NUMBER, areaCode, areaSpecificCode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }

    @OnClick(R.id.search_tour)
    public void onClickedSearchTourButton(View view) {
        pageNumber = 1;
        clearRecyclerViewItem();
        getTourInfo();
    }

    private void clearRecyclerViewItem() {
        tourInfoList.clear();
        tourInfoRecyclerViewAdapter.notifyDataSetChanged();
    }

    @OnItemSelected(R.id.city_spinner)
    public void onCitySpinnerSelect(int position) {
        Region region = Region.getRegionOrDefault(position);
        areaCode = region.getAreaCode();
        changeSpinnerItem(region.getSigunguArrayID(), detailCitySpinner);
    }

    @OnItemSelected(R.id.detail_city_spinner)
    public void onCityDetailSpinnerSelect(int position, Spinner spinner) {
        sigunguCode = position + 1;
    }

    public void changeSpinnerItem(int arrayId, Spinner spinner) {
        String[] items = getResources().getStringArray(arrayId);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).showProgressDialog(R.string.loading);
    }

    @Override
    public void hideLoading() {
        ((MainActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void showMessage(String message) {
        ToastUtil.getInstance().makeShort(message);
    }

    @Override
    public void showMessage(int message) {
        ToastUtil.getInstance().makeShort(message);
    }

    @Override
    public void showTourInfoList(List<TourInfo> tourInfoList) {
        this.tourInfoList.addAll(tourInfoList);
        tourInfoRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(TourSpotPresenter presenter) {
        this.tourSpotPresenter = presenter;
    }
}
