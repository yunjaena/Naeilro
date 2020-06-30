package com.koreatech.naeilro.ui.festival.presenter;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.event.Festival;
import com.koreatech.naeilro.network.entity.house.HouseInfo;
import com.koreatech.naeilro.network.interactor.FestivalInteractor;
import com.koreatech.naeilro.ui.festival.FestivalInfoFragmentContract;

import java.util.List;

public class FestivalFragmentPresenter {
    public static final String TAG = "EventPresenter";
    private FestivalInteractor festivalInteractor;
    private FestivalInfoFragmentContract.View festivalView;
    public FestivalFragmentPresenter(FestivalInteractor eventInteractor, FestivalInfoFragmentContract.View eventView) {
        this.festivalInteractor = eventInteractor;
        this.festivalView = eventView;
        eventView.setPresenter(this);
    }

    final ApiCallback festivalItemsApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<Festival> festivalItems = (List<Festival>) object;
            festivalView.showHouseList(festivalItems);
            festivalView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };

    final ApiCallback festivalCategoryItemsApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<Festival> festivalItems = (List<Festival>) object;
            festivalView.showHouseList(festivalItems);
            festivalView.hideLoading();

        }

        @Override
        public void onFailure(Throwable throwable) {
            Log.e("fail", throwable.getMessage());
        }
    };

    public void getFestivalItems(int pageNo){
        festivalView.showLoading();
        festivalInteractor.getFestivalImtes(festivalItemsApiCallback, 20, pageNo, "nailro", "A", "Y");
    }
    public void getFestivalCategoryItems(int pageNo, int areaCode, int sigunguCode) {
        festivalView.showLoading();
        festivalInteractor.getFestivalCategoryItems(festivalCategoryItemsApiCallback, 20, pageNo, "nailro", "A", "Y", areaCode, sigunguCode);
    }







}
