package com.koreatech.naeilro.ui.search.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.search.SearchInfo;
import com.koreatech.naeilro.network.interactor.SearchInteractor;

import java.util.List;

public class SearchPresenter {
    private SearchContract.View searchView;
    final ApiCallback searchApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<SearchInfo> searchInfoList = (List<SearchInfo>) object;
            if (searchInfoList.isEmpty()) {
                searchView.showEmptyItem();
            } else {
                searchView.showSearchInfoList(searchInfoList);
            }
            searchView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            searchView.showEmptyItem();
            searchView.hideLoading();
        }
    };
    private SearchInteractor searchInteractor;

    public SearchPresenter(SearchContract.View searchView, SearchInteractor searchInteractor) {
        this.searchView = searchView;
        this.searchInteractor = searchInteractor;
        this.searchView.setPresenter(this);
    }

    public void searchItemWithKeyword(String keyword, int type, int page) {
        searchView.showLoading();
        searchInteractor.getSearchContentByKeyWord(searchApiCallback, type, keyword, page);
    }
}
