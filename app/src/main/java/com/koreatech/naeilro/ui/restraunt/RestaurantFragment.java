package com.koreatech.naeilro.ui.restraunt;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantInfo;
import com.koreatech.naeilro.network.interactor.RestaurantRestInteractor;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.restraunt.adapater.RestaurantInfoRecyclerViewAdapter;
import com.koreatech.naeilro.ui.restraunt.presenter.RestaurantContract;
import com.koreatech.naeilro.ui.restraunt.presenter.RestaurantPresenter;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;


public class RestaurantFragment extends Fragment implements RestaurantContract.View, TMapView.OnCalloutRightButtonClickCallback {
    public static final String TAG = "RestaurantFragment";
    private static final double centerLon = 127.48318433761597;
    private static final double centerLat = 36.41592967015607;
    private LinearLayout tMapLinearLayout;
    private TMapView tMapView;
    private SearchView restaurantSearchView;
    private RecyclerView restaurantRecyclerView;
    private RestaurantPresenter restaurantPresenter;
    private List<RestaurantInfo> restaurantInfoList;
    private RestaurantInfoRecyclerViewAdapter restaurantInfoRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restraunt, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    private void init(View view) {
        restaurantRecyclerView = view.findViewById(R.id.restaurant_recycler_view);
        restaurantPresenter = new RestaurantPresenter(this, new RestaurantRestInteractor());
        initTMap(view);
        initSearchView(view);
        initRecyclerView(view);
    }

    private void initRecyclerView(View view) {
        restaurantInfoList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        restaurantInfoRecyclerViewAdapter = new RestaurantInfoRecyclerViewAdapter(restaurantInfoList, getContext());
        restaurantInfoRecyclerViewAdapter.setRecyclerClickListener(new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                goToRestaurantDetail(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        restaurantRecyclerView.setLayoutManager(linearLayoutManager);
        restaurantRecyclerView.setAdapter(restaurantInfoRecyclerViewAdapter);
    }


    private void initSearchView(View view) {
        restaurantSearchView = view.findViewById(R.id.restaurant_search_view);
        restaurantSearchView.setSubmitButtonEnabled(true);
        restaurantSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    hideKeyBoard(getActivity());
                    restaurantPresenter.getRestaurantSearchInfo(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }


    private void goToRestaurantDetail(int position) {
       // ToastUtil.getInstance().makeShort(String.valueOf(position));
    }

    private void initTMap(View view) {
        tMapLinearLayout = view.findViewById(R.id.restaurant_t_map_linear_layout);
        tMapView = new TMapView(Objects.requireNonNull(getActivity()));
        tMapView.setSKTMapApiKey(NaeilroApplication.getTMapApiKey());
        tMapLinearLayout.addView(tMapView);
        tMapView.setZoomLevel(6);
        tMapView.setCenterPoint(centerLon, centerLat);
        tMapView.setOnCalloutRightButtonClickListener(this);
    }

    private void hideKeyBoard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
        addMarker(restaurantInfoList);
        addRecyclerViewItem(restaurantInfoList);
    }

    @Override
    public void setPresenter(RestaurantPresenter presenter) {
        this.restaurantPresenter = presenter;
    }

    @Override
    public void onCalloutRightButton(TMapMarkerItem tMapMarkerItem) {
        String markerName = tMapMarkerItem.getName();
        goToRestaurantDetail(searchRestaurantPosition(markerName));
    }

    private int searchRestaurantPosition(String name) {
        for (int i = 0; i < restaurantInfoList.size(); i++) {
            if (restaurantInfoList.get(i).getTitle().equals(name))
                return i;
        }
        return 0;
    }

    private void addMarker(List<RestaurantInfo> restaurantInfoList) {
        Log.d(TAG, "showRestaurantInfoList: " + restaurantInfoList.size());
        tMapView.removeAllMarkerItem();
        Bitmap rightClickBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_zoom_in_white_18dp);
        for (RestaurantInfo restaurantInfo : restaurantInfoList) {
            TMapMarkerItem markerItem = new TMapMarkerItem();
            TMapPoint tMapPoint1 = new TMapPoint(restaurantInfo.getMapY(), restaurantInfo.getMapX());
            markerItem.setVisible(TMapMarkerItem.VISIBLE);
            markerItem.setPosition(0f, 0f);
            markerItem.setTMapPoint(tMapPoint1);
            markerItem.setName(restaurantInfo.getTitle());
            markerItem.setCanShowCallout(true);
            markerItem.setCalloutTitle(restaurantInfo.getTitle());
            markerItem.setCalloutSubTitle(restaurantInfo.getAddress());
            tMapView.addMarkerItem(restaurantInfo.getTitle(), markerItem);
        }
    }

    private void addRecyclerViewItem(List<RestaurantInfo> restaurantInfoList) {
        this.restaurantInfoList.clear();
        this.restaurantInfoList.addAll(restaurantInfoList);
        restaurantInfoRecyclerViewAdapter.notifyDataSetChanged();
    }
}
