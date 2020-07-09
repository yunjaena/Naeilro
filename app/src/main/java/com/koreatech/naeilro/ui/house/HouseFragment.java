package com.koreatech.naeilro.ui.house;

import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.house.HouseInfo;
import com.koreatech.naeilro.network.interactor.HouseRestInteractor;
import com.koreatech.naeilro.ui.house.adapter.HouseInfoRecyclerViewAdapter;
import com.koreatech.naeilro.ui.house.presenter.HouseFragmentPresenter;
import com.koreatech.naeilro.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;


public class HouseFragment extends Fragment implements HouseInfoFragmentContract.View {
    private HouseFragmentPresenter houseFragmentPresenter;
    private RecyclerView houseInfoRecyclerView;
    private HouseInfoRecyclerViewAdapter houseInfoRecyclerViewAdapter;
    private int pageNum;
    private int totlaCount;
    private View view;
    private Unbinder unbinder;
    private int areaCode;
    private int sigunguCode;
    boolean filterFlag = false;

    @BindView(R.id.city_spinner)
    Spinner citySpinner;
    @BindView(R.id.detail_city_spinner)
    Spinner detailSpinner;
    @BindView(R.id.search_house)
    Button houseSearchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_house, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        init(view);
        houseFragmentPresenter.getHouseTotalCount();
        houseFragmentPresenter.getHouseItems(pageNum);
        return view;

    }

    public void init(View view) {
        totlaCount = 0;
        pageNum = 1;
        houseFragmentPresenter = new HouseFragmentPresenter(new HouseRestInteractor(), this);
        houseInfoRecyclerView = view.findViewById(R.id.house_info_recycler_view);
        houseInfoRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        //houseInfoRecyclerView.setHasFixedSize(false);
        //houseInfoRecyclerView.setNestedScrollingEnabled(false);
        houseInfoRecyclerViewAdapter = new HouseInfoRecyclerViewAdapter(view.getContext());
        houseInfoRecyclerView.setAdapter(houseInfoRecyclerViewAdapter);
        houseInfoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = recyclerView.getAdapter().getItemCount() - 1;
                int lastVisible = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                if (lastVisible >= pageNum * 20 - 1) {
                    if (areaCode == 0) {
                        pageNum += 1;
                        houseFragmentPresenter.getHouseItems(pageNum);
                    } else {
                        pageNum += 1;
                        if (areaCode == 34 && sigunguCode > 9) {
                            houseFragmentPresenter.getHouseCategoryItems(pageNum, areaCode, sigunguCode + 1);
                        }
                        else if (areaCode == 36 && sigunguCode > 10) {
                            houseFragmentPresenter.getHouseCategoryItems(pageNum, areaCode, sigunguCode + 1);
                        }else if (areaCode == 38 && sigunguCode > 13) {
                            houseFragmentPresenter.getHouseCategoryItems(pageNum, areaCode, sigunguCode + 2);
                        }
                        else
                            houseFragmentPresenter.getHouseCategoryItems(pageNum, areaCode, sigunguCode);
                    }
                }
            }
        });

    }

    @OnClick(R.id.search_house)
    public void getSearchHouse() {
        filterFlag = true;
        pageNum = 1;
        if (areaCode != 0) {
            if (areaCode == 34 && sigunguCode > 9) {
                houseFragmentPresenter.getHouseCategoryItems(pageNum, areaCode, sigunguCode + 1);
            }
            else if (areaCode == 36 && sigunguCode > 10) {
                houseFragmentPresenter.getHouseCategoryItems(pageNum, areaCode, sigunguCode + 1);
            }else if (areaCode == 38 && sigunguCode > 13) {
                houseFragmentPresenter.getHouseCategoryItems(pageNum, areaCode, sigunguCode + 2);
            }
            else
                houseFragmentPresenter.getHouseCategoryItems(pageNum, areaCode, sigunguCode);
        }
        else
            houseFragmentPresenter.getHouseItems(pageNum);
    }

    @OnItemSelected(R.id.city_spinner)
    public void onCitySpinnerSelet(int position) {
        switch (position) {
            case 0:
                areaCode = 0;
                changeSpinnerItem(R.array.default_array, detailSpinner);
                break;
            case 1:
                areaCode = 1;
                changeSpinnerItem(R.array.seoul_array, detailSpinner);
                break;
            case 2:
                areaCode = 2;
                changeSpinnerItem(R.array.incheon_array, detailSpinner);
                break;
            case 3:
                areaCode = 3;
                changeSpinnerItem(R.array.dajeon_array, detailSpinner);
                break;
            case 4:
                areaCode = 4;
                changeSpinnerItem(R.array.daegu_array, detailSpinner);
                break;
            case 5:
                areaCode = 5;
                changeSpinnerItem(R.array.gangju_array, detailSpinner);
                break;
            case 6:
                areaCode = 6;
                changeSpinnerItem(R.array.busan_array, detailSpinner);
                break;
            case 7:
                areaCode = 7;
                changeSpinnerItem(R.array.ulsan_array, detailSpinner);
                break;
            case 8:
                areaCode = 8;
                changeSpinnerItem(R.array.saejong_array, detailSpinner);
                break;
            case 9:
                areaCode = 31;
                changeSpinnerItem(R.array.gunggi_array, detailSpinner);
                break;
            case 10:
                areaCode = 32;
                changeSpinnerItem(R.array.gangwon_array, detailSpinner);
                break;
            case 11:
                areaCode = 33;
                changeSpinnerItem(R.array.chungchungbuk_array, detailSpinner);
                break;
            case 12:
                areaCode = 34;
                changeSpinnerItem(R.array.chungchungnam_array, detailSpinner);
                break;
            case 13:
                areaCode = 35;
                changeSpinnerItem(R.array.gyungsangbuk_array, detailSpinner);
                break;
            case 14:
                areaCode = 36;
                changeSpinnerItem(R.array.gyungsangnam_array, detailSpinner);
                break;
            case 15:
                areaCode = 37;
                changeSpinnerItem(R.array.junrabuk_array, detailSpinner);
                break;
            case 16:
                areaCode = 38;
                changeSpinnerItem(R.array.junranam_array, detailSpinner);
                break;
            case 17:
                areaCode = 39;
                changeSpinnerItem(R.array.jeju_array, detailSpinner);
                break;
        }
    }

    @OnItemSelected(R.id.detail_city_spinner)
    public void onCityDetailSpinnerSelet(int position, Spinner spinner) {
        sigunguCode = position + 1;
    }


    @Override
    public void setPresenter(HouseFragmentPresenter presenter) {
        this.houseFragmentPresenter = presenter;
    }

    @Override
    public void setTotalCount(int count) {
        totlaCount = count;
    }

    @Override
    public void showHouseList(List<HouseInfo> houseList) {
        if (filterFlag == true) {
            houseInfoRecyclerViewAdapter.clearItem();
            houseInfoRecyclerViewAdapter.addItem(houseList);
            filterFlag = false;
        } else {
            houseInfoRecyclerViewAdapter.addItem(houseList);
        }


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
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }

    public void changeSpinnerItem(int arrayId, Spinner spinner) {
        String[] items = getResources().getStringArray(arrayId);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(spinnerAdapter);
    }
}
