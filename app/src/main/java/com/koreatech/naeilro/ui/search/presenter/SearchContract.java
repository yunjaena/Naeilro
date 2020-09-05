package com.koreatech.naeilro.ui.search.presenter;

import androidx.annotation.StringRes;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.search.SearchInfo;

import java.util.List;

public interface SearchContract {
    interface View extends BaseView<SearchPresenter> {
        void showLoading();

        void hideLoading();

        void showMessage(String message);

        void showMessage(@StringRes int message);

        void showSearchInfoList(List<SearchInfo> searchInfoList);

        void showEmptyItem();
    }
}
