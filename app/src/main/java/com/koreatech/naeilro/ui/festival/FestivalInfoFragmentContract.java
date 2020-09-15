package com.koreatech.naeilro.ui.festival;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.event.Festival;
import com.koreatech.naeilro.ui.festival.presenter.FestivalFragmentPresenter;

import java.util.List;

public interface FestivalInfoFragmentContract {
    interface View extends BaseView<FestivalFragmentPresenter> {
        void showHouseList(List<Festival> houseList);
        void showLoading();
        void hideLoading();
    }
}
