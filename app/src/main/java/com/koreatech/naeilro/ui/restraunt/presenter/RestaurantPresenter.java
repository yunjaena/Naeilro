package com.koreatech.naeilro.ui.restraunt.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantInfo;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantList;
import com.koreatech.naeilro.network.interactor.RestaurantInteractor;

import java.util.List;

public class RestaurantPresenter {
    private RestaurantContract.View restaurantView;
    private RestaurantInteractor restaurantInteractor;
    final ApiCallback RestaurantItemApiCallBack = new ApiCallback() {
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
    final ApiCallback restaurantCategoryItemApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<RestaurantInfo> restaurantItems = (List<RestaurantInfo>) object;
            restaurantView.showRestaurantInfoList(restaurantItems);
            restaurantView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };


    public RestaurantPresenter(RestaurantContract.View restaurantView, RestaurantInteractor restaurantInteractor) {
        this.restaurantView = restaurantView;
        this.restaurantInteractor = restaurantInteractor;
        restaurantView.setPresenter(this);
    }

    public void getRestaurantItems(int pageNo) {
        restaurantView.showLoading();
        restaurantInteractor.getRestaurantImtes(RestaurantItemApiCallBack,20,pageNo, "nailro", "A","Y");
    }
    public void getRestaurantCategoryItems(int pageNo, int areaCode, int sigunguCode){
        restaurantView.showLoading();
        restaurantInteractor.getRestaurantCategoryItems(restaurantCategoryItemApiCallback,20,pageNo, "nailro", "A","Y",areaCode,sigunguCode);
    }
}
