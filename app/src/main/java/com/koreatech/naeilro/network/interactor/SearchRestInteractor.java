package com.koreatech.naeilro.network.interactor;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.KoreanTourRetrofitManager;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.network.entity.search.SearchInfo;
import com.koreatech.naeilro.network.entity.search.SearchList;
import com.koreatech.naeilro.network.service.SearchService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class SearchRestInteractor implements SearchInteractor {
    public static final String TAG = "SearchRestInteractor";

    @Override
    public void getSearchContentByKeyWord(ApiCallback apiCallback, int contentType, String keyWord, int page) {
        String apiKey = NaeilroApplication.getDataApiKey();
        List<SearchInfo> searchInfoList = new ArrayList<>();
        KoreanTourRetrofitManager.getInstance().getRetrofit().create(SearchService.class).
                getSearchByKeyword(apiKey, 100, 1, "AND", "Naeilro", "A", "Y", contentType, keyWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchList searchList) {
                        if (searchList != null) {
                            searchInfoList.addAll(searchList.getSearchInfoInfoBodyList().get(0).getSearchInfoItemList().get(0).getSearchInfoList());
                            apiCallback.onSuccess(searchInfoList);
                        } else {
                            apiCallback.onFailure(new Throwable("fail read searchList"));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            Log.d(TAG, ((HttpException) e).code() + " ");
                        }
                        apiCallback.onFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
