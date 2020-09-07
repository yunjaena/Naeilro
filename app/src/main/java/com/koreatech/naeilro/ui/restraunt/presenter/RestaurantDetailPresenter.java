package com.koreatech.naeilro.ui.restraunt.presenter;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantInfo;
import com.koreatech.naeilro.network.interactor.RestaurantInteractor;
import com.koreatech.naeilro.ui.restraunt.RestaurantDetailFragment;

import java.util.List;

public class RestaurantDetailPresenter {
    private RestaurantInteractor restaurantInteractor;
    private RestaurantDetailContract.View restaurantDetailView;

    public RestaurantDetailPresenter(RestaurantInteractor restaurantInteractor, RestaurantDetailContract.View restaurantDetailView) {
        this.restaurantInteractor = restaurantInteractor;
        this.restaurantDetailView = restaurantDetailView;
        restaurantDetailView.setPresenter(this);
    }
    final ApiCallback retaurantCommonApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            RestaurantInfo restaurantInfo = (RestaurantInfo) object;
            restaurantDetailView.showCommonInfo(restaurantInfo);
        }

        @Override
        public void onFailure(Throwable throwable) {
            Log.e("presenter",throwable.getMessage());
        }
    };
    final ApiCallback restaurantDetailInfoApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            Log.e("presenter","detaioInfo");
            RestaurantInfo restaurantItems = (RestaurantInfo) object;
            restaurantDetailView.showDetailInfoList(restaurantItems);
        }

        @Override
        public void onFailure(Throwable throwable) {
            Log.e("presenter",throwable.getMessage());
            restaurantDetailView.hideLoading();
        }
    };
    final ApiCallback restaurantImageInfoApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            Log.e("presenter","image");
            List<RestaurantInfo> restaurantItems = (List<RestaurantInfo>) object;
            restaurantDetailView.showImageInfoList(restaurantItems);
            restaurantDetailView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            Log.e("presenter",throwable.getMessage());
            restaurantDetailView.hideLoading();
        }
    };
    public void getCommonInfo(int contentId){
        restaurantDetailView.showLoading();
        restaurantInteractor.getCommonItems(retaurantCommonApiCallback, contentId, "AND", "nailro");
    }
    public void getDetailInfo(int contentId){
        Log.e("presenter","getdetailInfo");
        restaurantInteractor.getDetailItems(restaurantDetailInfoApiCallback, contentId, "AND", "nailro");
    }
    public void getImageInfo(int contentId){
        Log.e("presenter","getImage");
        restaurantInteractor.getImageItems(restaurantImageInfoApiCallback,contentId,"AND","nailro");
    }
}
