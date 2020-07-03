package com.koreatech.naeilro.ui.festival.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.event.Festival;
import com.koreatech.naeilro.network.interactor.FestivalInteractor;
import com.koreatech.naeilro.ui.festival.FestivalDetailFragment;
import com.koreatech.naeilro.ui.festival.FestivalDetailFragmentContract;

public class FestivalDetailFragmentPresenter {
    public static final String TAG = "FestivalDetailFragmentPresenter";
    private FestivalInteractor festivalInteractor;
    private FestivalDetailFragmentContract.View festivalDetailView;

    public FestivalDetailFragmentPresenter(FestivalInteractor festivalInteractor, FestivalDetailFragmentContract.View festivalDetailView) {
        this.festivalInteractor = festivalInteractor;
        this.festivalDetailView = festivalDetailView;
        festivalDetailView.setPresenter(this);
    }
    final ApiCallback festivalCommonApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            Festival festival = (Festival) object;
            festivalDetailView.showFestivalCommon(festival);
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };
    final ApiCallback festivalIntroApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            Festival festival = (Festival) object;
            festivalDetailView.showFestivalIntro(festival);
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };

    public void getFestivalCommonInfo(int contentTypeId, int contentId){
        festivalInteractor.getFestivalCommonItems(festivalCommonApiCallback, contentTypeId, contentId, "nailro", "Y","Y","Y","Y","Y");
    }
    public void getFestivalIntroInfo(int contetnTypeId, int contentId){
        festivalInteractor.getFestivalIntroItems(festivalIntroApiCallback, contetnTypeId, contentId, "nailro");
    }

}
