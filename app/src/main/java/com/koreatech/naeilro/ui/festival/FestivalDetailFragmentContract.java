package com.koreatech.naeilro.ui.festival;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.event.Festival;
import com.koreatech.naeilro.ui.festival.presenter.FestivalDetailFragmentPresenter;

public interface FestivalDetailFragmentContract {
    interface View extends BaseView<FestivalDetailFragmentPresenter> {
        void showFestivalCommon(Festival festival);
        void showFestivalIntro(Festival festival);
    }
}
