package com.koreatech.naeilro.ui.festival;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.event.Festival;
import com.koreatech.naeilro.network.interactor.FestivalRestInteractor;
import com.koreatech.naeilro.ui.festival.adapter.FestivalInfoRecyclerViewAdapter;
import com.koreatech.naeilro.ui.festival.presenter.FestivalFragmentPresenter;
import com.koreatech.naeilro.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;


public class FestivalFragment extends Fragment implements FestivalInfoFragmentContract.View{
    private View view;
    private FestivalFragmentPresenter festivalFragmentPresenter;
    private List<Festival> eventList;
    private RecyclerView festivalInfoRecyclerView;
    private FestivalInfoRecyclerViewAdapter festivalInfoRecyclerViewAdapter;
    private int pageNum;
    private Unbinder unbinder;
    private int areaCode;
    private int sigunguCode;
    boolean filterFlag = false;

    @BindView(R.id.festival_city_spinner)
    Spinner citySpinner;
    @BindView(R.id.festival_detail_city_spinner)
    Spinner detailSpinner;
    @BindView(R.id.search_festival)
    Button festivalSearchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_festival, container, false);
        init(view);
        this.unbinder = ButterKnife.bind(this, view);
        festivalFragmentPresenter.getFestivalItems(pageNum);
        return view;
    }



    @Override
    public void showHouseList(List<Festival> festivalList) {
        if (filterFlag == true) {
            festivalInfoRecyclerViewAdapter.clearItem();
            festivalInfoRecyclerViewAdapter.addItem(festivalList);
            filterFlag = false;
        } else {
            festivalInfoRecyclerViewAdapter.addItem(festivalList);
        }
    }

    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).showProgressDialog(R.string.loading_festival_info);
    }

    @Override
    public void hideLoading() {
        ((MainActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder != null) unbinder.unbind();
    }

    @Override
    public void setPresenter(FestivalFragmentPresenter presenter) {
        this.festivalFragmentPresenter = presenter;

    }
    public void init(View view){
        pageNum = 1;
        festivalFragmentPresenter = new FestivalFragmentPresenter(new FestivalRestInteractor(), this);
        eventList = new ArrayList<>();
        festivalInfoRecyclerView = view.findViewById(R.id.festival_info_recycler_view);
        festivalInfoRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        festivalInfoRecyclerViewAdapter = new FestivalInfoRecyclerViewAdapter();
        festivalInfoRecyclerView.setAdapter(festivalInfoRecyclerViewAdapter);
        festivalInfoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = recyclerView.getAdapter().getItemCount()-1;
                int lastVisible = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                if (lastVisible >= pageNum * 20 - 1) {
                    if (areaCode == 0) {
                        pageNum += 1;
                        festivalFragmentPresenter.getFestivalItems(pageNum);
                    } else {
                        pageNum += 1;
                        festivalFragmentPresenter.getFestivalCategoryItems(pageNum, areaCode, sigunguCode);
                    }
                }
            }
        });
    }
    @OnClick(R.id.search_festival)
    public void getSearchFestival() {
        filterFlag = true;
        pageNum = 1;
        if (areaCode != 0)
            festivalFragmentPresenter.getFestivalCategoryItems(pageNum, areaCode, sigunguCode);
        else
            festivalFragmentPresenter.getFestivalItems(pageNum);
    }
    @OnItemSelected(R.id.festival_city_spinner)
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
    @OnItemSelected(R.id.festival_detail_city_spinner)
    public void onCityDetailSpinnerSelet(int position, Spinner spinner) {
        sigunguCode = position + 1;
        Log.e("position", Integer.toString(position));
    }
    public void changeSpinnerItem(int arrayId, Spinner spinner) {
        String[] items = getResources().getStringArray(arrayId);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(spinnerAdapter);
    }
}
