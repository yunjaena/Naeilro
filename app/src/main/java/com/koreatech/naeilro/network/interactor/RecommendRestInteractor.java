package com.koreatech.naeilro.network.interactor;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.MyPlanRetrofitManager;
import com.koreatech.naeilro.auth.JWTTokenManager;
import com.koreatech.naeilro.network.entity.recommend.Recommend;
import com.koreatech.naeilro.network.service.RecommendService;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class RecommendRestInteractor implements RecommendInteractor {
    @Override
    public void getRecommend(ApiCallback apiCallback) {
        String token = JWTTokenManager.getInstance().getAccessToken();
        if(token == null){
            apiCallback.onFailure(new Throwable("세션이 만료되었습니다."));
            return;
        }

        MyPlanRetrofitManager.getInstance().getRetrofit().create(RecommendService.class).getRecommend(MyPlanRetrofitManager.addAuthorizationBearer(token))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Recommend>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Recommend recommend) {
                        if(recommend != null && recommend.isSuccess()){
                            apiCallback.onSuccess(recommend.getMyRecommendItemList());
                        }
                        else
                            apiCallback.onFailure(new Throwable(recommend.getMessage()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            Log.d("TAG", ((HttpException) e).code() + " ");
                        }
                        apiCallback.onFailure(new Throwable("통신에 실패하였습니다."));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
