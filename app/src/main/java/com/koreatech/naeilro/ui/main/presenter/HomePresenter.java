package com.koreatech.naeilro.ui.main.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.recommend.MyRecommendItem;
import com.koreatech.naeilro.network.interactor.RecommendInteractor;
import com.koreatech.naeilro.ui.main.HomeFragmentContract;

import java.util.List;

public class HomePresenter {
    private RecommendInteractor recommendInteractor;
    private HomeFragmentContract.View homeView;

    public HomePresenter(RecommendInteractor recommendInteractor, HomeFragmentContract.View homeView) {
        this.recommendInteractor = recommendInteractor;
        this.homeView = homeView;
        homeView.setPresenter(this);
    }
    final ApiCallback recommendApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<MyRecommendItem> recommendItems = (List<MyRecommendItem>) object;
            homeView.showRecommendList(recommendItems);
            homeView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            homeView.showFailure(throwable.getMessage());
        }
    };

    public void getRecommendList(){
        homeView.showLoading();
        recommendInteractor.getRecommend(recommendApiCallback);
    }
}
