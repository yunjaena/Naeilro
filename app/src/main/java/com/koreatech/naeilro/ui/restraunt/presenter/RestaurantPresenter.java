package com.koreatech.naeilro.ui.restraunt.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantInfo;
import com.koreatech.naeilro.network.interactor.RestaurantInteractor;

import java.util.List;

public class RestaurantPresenter {
    private RestaurantContract.View restaurantView;
    final ApiCallback searchRestaurantApiCallBack = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            if (object != null) {
                List<RestaurantInfo> restaurantInfoList = (List<RestaurantInfo>) object;
                restaurantView.showRestaurantInfoList(restaurantInfoList);
            }
            restaurantView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            restaurantView.hideLoading();
        }
    };
    private RestaurantInteractor restaurantInteractor;

    public RestaurantPresenter(RestaurantContract.View restaurantView, RestaurantInteractor restaurantInteractor) {
        this.restaurantView = restaurantView;
        this.restaurantInteractor = restaurantInteractor;
        restaurantView.setPresenter(this);
    }

    public void getRestaurantSearchInfo(String searchText) {
        if (searchText == null) return;
        restaurantView.showLoading();
        restaurantInteractor.searchRestaurantInfo(searchText, searchRestaurantApiCallBack);
    }
}
