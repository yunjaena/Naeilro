package com.koreatech.naeilro.ui.facility;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.interactor.FacilityRestInteractor;
import com.koreatech.naeilro.ui.facility.adapter.FacilityInfoRecyclerViewAdapter;
import com.koreatech.naeilro.ui.facility.presenter.FacilityFragmentPresenter;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.reports.adapter.ReportsInfoRecyclerViewAdapter;
import com.koreatech.naeilro.ui.reports.presenter.ReportsFragmentPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;


public class FacilityFragment extends Fragment implements FacilityFragmentContract.View {
    private View view;
    private FacilityFragmentPresenter facilityFragmentPresenter;
    private RecyclerView facilityRecyclerView;
    private FacilityInfoRecyclerViewAdapter facilityInfoRecyclerViewAdapter;
    private int pageNum;
    private Unbinder unbinder;
    private int areaCode;
    private int sigunguCode;
    boolean filterFlag = false;

    @BindView(R.id.facility_city_spinner)
    Spinner citySpiner;
    @BindView(R.id.facility_detail_city_spinner)
    Spinner detailSpinner;
    @BindView(R.id.search_facility)
    Button facilitySearchButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_facility, container, false);
        init(view);
        this.unbinder = ButterKnife.bind(this, view);
        facilityFragmentPresenter.getFacilityItems(pageNum);
        return view;
    }
    public void init(View view){
        pageNum = 1;
        facilityFragmentPresenter = new FacilityFragmentPresenter(new FacilityRestInteractor(), this);
        facilityRecyclerView = view.findViewById(R.id.facility_info_recycler_view);
        facilityRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        facilityInfoRecyclerViewAdapter = new FacilityInfoRecyclerViewAdapter(view.getContext());
        facilityRecyclerView.setAdapter(facilityInfoRecyclerViewAdapter);
        facilityRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisible = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                if (lastVisible >= pageNum * 20 - 1) {
                    if (areaCode == 0) {
                        pageNum += 1;
                        facilityFragmentPresenter.getFacilityItems(pageNum);
                    } else {
                        pageNum += 1;
                        if (areaCode == 34 && sigunguCode > 9) {
                            facilityFragmentPresenter.getFacilityCategoryItems(pageNum, areaCode, sigunguCode + 1);
                        } else if (areaCode == 36 && sigunguCode > 10) {
                            facilityFragmentPresenter.getFacilityCategoryItems(pageNum, areaCode, sigunguCode + 1);
                        } else if (areaCode == 38 && sigunguCode > 13) {
                            facilityFragmentPresenter.getFacilityCategoryItems(pageNum, areaCode, sigunguCode + 2);
                        } else
                            facilityFragmentPresenter.getFacilityCategoryItems(pageNum, areaCode, sigunguCode);
                    }
                }
            }
        });

    }

    @Override
    public void showFacilityList(List<Facility> facilityList) {
        if (filterFlag == true) {
            facilityInfoRecyclerViewAdapter.clearItem();
            facilityInfoRecyclerViewAdapter.addItem(facilityList);
            filterFlag = false;
        } else {
            facilityInfoRecyclerViewAdapter.addItem(facilityList);
        }
    }

    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).showProgressDialog(R.string.loading_facility_info);
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
    @Override
    public void setPresenter(FacilityFragmentPresenter presenter) {
        this.facilityFragmentPresenter = presenter;
    }
    @OnClick(R.id.search_facility)
    public void getSearchFacility() {
        filterFlag = true;
        pageNum = 1;
        if (areaCode != 0) {
            if (areaCode == 34 && sigunguCode > 9) {
                facilityFragmentPresenter.getFacilityCategoryItems(pageNum, areaCode, sigunguCode + 1);
            }
            else if (areaCode == 36 && sigunguCode > 10) {
                facilityFragmentPresenter.getFacilityCategoryItems(pageNum, areaCode, sigunguCode + 1);
            }else if (areaCode == 38 && sigunguCode > 13) {
                facilityFragmentPresenter.getFacilityCategoryItems(pageNum, areaCode, sigunguCode + 2);
            }
            else
                facilityFragmentPresenter.getFacilityCategoryItems(pageNum, areaCode, sigunguCode);
        }
        else
            facilityFragmentPresenter.getFacilityItems(pageNum);
    }
    @OnItemSelected(R.id.facility_city_spinner)
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
    @OnItemSelected(R.id.facility_detail_city_spinner)
    public void onCityDetailSpinnerSelet(int position, Spinner spinner) {
        sigunguCode = position + 1;
    }
    public void changeSpinnerItem(int arrayId, Spinner spinner) {
        String[] items = getResources().getStringArray(arrayId);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(spinnerAdapter);
    }
}
