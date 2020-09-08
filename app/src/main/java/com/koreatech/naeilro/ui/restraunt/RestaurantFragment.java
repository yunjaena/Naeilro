package com.koreatech.naeilro.ui.restraunt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantInfo;
import com.koreatech.naeilro.network.interactor.RestaurantRestInteractor;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.restraunt.adapater.RestaurantInfoRecyclerViewAdapter;
import com.koreatech.naeilro.ui.restraunt.presenter.RestaurantContract;
import com.koreatech.naeilro.ui.restraunt.presenter.RestaurantPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;


public class RestaurantFragment extends Fragment implements RestaurantContract.View {
    public static final String TAG = "RestaurantFragment";
    private static final double centerLon = 127.48318433761597;
    private static final double centerLat = 36.41592967015607;
    boolean filterFlag = false;
    @BindView(R.id.restaurant_city_spinner)
    Spinner citySpiner;
    @BindView(R.id.restaurant_detail_city_spinner)
    Spinner detailSpinner;
    @BindView(R.id.search_restaurant)
    Button restaurantSearchButton;
    private LinearLayout tMapLinearLayout;
    private com.skt.Tmap.TMapView tMapView;
    private SearchView restaurantSearchView;
    private View view;
    private RecyclerView restaurantRecyclerView;
    private RestaurantPresenter restaurantPresenter;
    private List<RestaurantInfo> restaurantInfoList;
    private RestaurantInfoRecyclerViewAdapter restaurantInfoRecyclerViewAdapter;
    private int pageNum;
    private Unbinder unbinder;
    private int areaCode;
    private int sigunguCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_restraunt, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        init(view);
        restaurantPresenter.getRestaurantItems(pageNum);
        return view;
    }

    private void init(View view) {
        pageNum = 1;
        restaurantPresenter = new RestaurantPresenter(this, new RestaurantRestInteractor());
        restaurantRecyclerView = view.findViewById(R.id.restaurant_info_recycler_view);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        restaurantInfoRecyclerViewAdapter = new RestaurantInfoRecyclerViewAdapter(view.getContext());
        restaurantRecyclerView.setAdapter(restaurantInfoRecyclerViewAdapter);
        restaurantRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisible = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                if (lastVisible >= pageNum * 20 - 1) {
                    if (areaCode == 0) {
                        pageNum += 1;
                        restaurantPresenter.getRestaurantItems(pageNum);
                    } else {
                        pageNum += 1;
                        if (areaCode == 34 && sigunguCode > 9) {
                            restaurantPresenter.getRestaurantCategoryItems(pageNum, areaCode, sigunguCode + 1);
                        } else if (areaCode == 36 && sigunguCode > 10) {
                            restaurantPresenter.getRestaurantCategoryItems(pageNum, areaCode, sigunguCode + 1);
                        } else if (areaCode == 38 && sigunguCode > 13) {
                            restaurantPresenter.getRestaurantCategoryItems(pageNum, areaCode, sigunguCode + 2);
                        } else
                            restaurantPresenter.getRestaurantCategoryItems(pageNum, areaCode, sigunguCode);
                    }
                }
            }
        });
    }

    @OnClick(R.id.search_restaurant)
    public void getSearchRestaurant() {
        filterFlag = true;
        pageNum = 1;
        if (areaCode != 0) {
            if (areaCode == 34 && sigunguCode > 9) {
                restaurantPresenter.getRestaurantCategoryItems(pageNum, areaCode, sigunguCode + 1);
            } else if (areaCode == 36 && sigunguCode > 10) {
                restaurantPresenter.getRestaurantCategoryItems(pageNum, areaCode, sigunguCode + 1);
            } else if (areaCode == 38 && sigunguCode > 13) {
                restaurantPresenter.getRestaurantCategoryItems(pageNum, areaCode, sigunguCode + 2);
            } else
                restaurantPresenter.getRestaurantCategoryItems(pageNum, areaCode, sigunguCode);
        } else
            restaurantPresenter.getRestaurantItems(pageNum);
    }

    @OnItemSelected(R.id.restaurant_city_spinner)
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

    @OnItemSelected(R.id.restaurant_detail_city_spinner)
    public void onCityDetailSpinnerSelet(int position, Spinner spinner) {
        sigunguCode = position + 1;
    }

    public void changeSpinnerItem(int arrayId, Spinner spinner) {
        String[] items = getResources().getStringArray(arrayId);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
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
    public void showRestaurantInfoList(List<RestaurantInfo> restaurantInfoList) {
        if (filterFlag == true) {
            restaurantInfoRecyclerViewAdapter.clearItem();
            restaurantInfoRecyclerViewAdapter.addItem(restaurantInfoList);
            filterFlag = false;
        } else {
            restaurantInfoRecyclerViewAdapter.addItem(restaurantInfoList);
        }
    }

    @Override
    public void setPresenter(RestaurantPresenter presenter) {
        this.restaurantPresenter = presenter;
    }


}
