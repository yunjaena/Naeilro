package com.koreatech.naeilro.network.interactor;

import android.util.ArrayMap;
import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.MyPlanRetrofitManager;
import com.koreatech.naeilro.auth.JWTTokenManager;
import com.koreatech.naeilro.network.entity.DefaultMessage;
import com.koreatech.naeilro.network.entity.myplan.MyPlan;
import com.koreatech.naeilro.network.entity.myplan.MyPlanNode;
import com.koreatech.naeilro.network.entity.myplan.MyPlanNodeResponse;
import com.koreatech.naeilro.network.entity.myplan.MyPlanResponse;
import com.koreatech.naeilro.network.service.MyPlanService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.HttpException;

public class MyPlanRestInteractor implements MyPlanInteractor {
    public static final String TAG = "MyPlanRestInteractor";

    @Override
    public void createNode(ApiCallback apiCallback, String planNumber, String contentID, String contentType, String contentTitle, String contentThumbnail, Float mapX, Float mapY, String areaCode) {
        Map<String, Object> jsonObject = new ArrayMap<>();
        jsonObject.put("plan_no", planNumber);
        jsonObject.put("content_id", contentID);
        jsonObject.put("content_type", contentType);
        jsonObject.put("thumbnail", contentThumbnail);
        jsonObject.put("title", contentTitle);
        jsonObject.put("areacode", areaCode);
        if (mapX != null && mapY != null) {
            jsonObject.put("mapX", mapX);
            jsonObject.put("mapY", mapY);
        }

        String token = JWTTokenManager.getInstance().getAccessToken();
        if (token == null) {
            apiCallback.onFailure(new Throwable("세션이 만료되었습니다."));
            return;
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonObject)).toString());
        MyPlanRetrofitManager.getInstance().getRetrofit().create(MyPlanService.class).createNode(MyPlanRetrofitManager.addAuthorizationBearer(token), body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DefaultMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DefaultMessage message) {
                        if (message != null && message.isSuccess()) {
                            apiCallback.onSuccess(message);
                        } else
                            apiCallback.onFailure(new Throwable(message.getMessage()));
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

    @Override
    public void deleteNode(ApiCallback apiCallback, String nodeNumber, String contentType, String contentId) {
        Map<String, Object> jsonObject = new ArrayMap<>();
        jsonObject.put("node_no", nodeNumber);
        jsonObject.put("content_type", contentType);
        jsonObject.put("content_id", contentId);


        String token = JWTTokenManager.getInstance().getAccessToken();
        if (token == null) {
            apiCallback.onFailure(new Throwable("세션이 만료되었습니다."));
            return;
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonObject)).toString());
        MyPlanRetrofitManager.getInstance().getRetrofit().create(MyPlanService.class).dropNode(MyPlanRetrofitManager.addAuthorizationBearer(token), body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DefaultMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DefaultMessage message) {
                        if (message != null && message.isSuccess()) {
                            apiCallback.onSuccess(message);
                        } else
                            apiCallback.onFailure(new Throwable(message.getMessage()));
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


    @Override
    public void createPlan(ApiCallback apiCallback, String planTitle) {
        Map<String, Object> jsonObject = new ArrayMap<>();
        jsonObject.put("plantitle", planTitle);


        String token = JWTTokenManager.getInstance().getAccessToken();
        if (token == null) {
            apiCallback.onFailure(new Throwable("세션이 만료되었습니다."));
            return;
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonObject)).toString());
        MyPlanRetrofitManager.getInstance().getRetrofit().create(MyPlanService.class).createPlan(MyPlanRetrofitManager.addAuthorizationBearer(token), body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DefaultMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DefaultMessage message) {
                        if (message != null && message.isSuccess()) {
                            apiCallback.onSuccess(message);
                        } else
                            apiCallback.onFailure(new Throwable(message.getMessage()));
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

    @Override
    public void deletePlan(ApiCallback apiCallback, String planNumber) {
        Map<String, Object> jsonObject = new ArrayMap<>();
        jsonObject.put("plan_no", planNumber);


        String token = JWTTokenManager.getInstance().getAccessToken();
        if (token == null) {
            apiCallback.onFailure(new Throwable("세션이 만료되었습니다."));
            return;
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonObject)).toString());
        MyPlanRetrofitManager.getInstance().getRetrofit().create(MyPlanService.class).dropPlan(MyPlanRetrofitManager.addAuthorizationBearer(token), body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DefaultMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DefaultMessage message) {
                        if (message != null && message.isSuccess()) {
                            apiCallback.onSuccess(message);
                        } else
                            apiCallback.onFailure(new Throwable(message.getMessage()));
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

    @Override
    public void getNode(ApiCallback apiCallback, String planNumber) {
        Map<String, Object> jsonObject = new ArrayMap<>();
        jsonObject.put("plan_no", planNumber);


        String token = JWTTokenManager.getInstance().getAccessToken();
        if (token == null) {
            apiCallback.onFailure(new Throwable("세션이 만료되었습니다."));
            return;
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonObject)).toString());
        MyPlanRetrofitManager.getInstance().getRetrofit().create(MyPlanService.class).getNode(MyPlanRetrofitManager.addAuthorizationBearer(token), body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyPlanNodeResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyPlanNodeResponse myPlanNodeResponse) {
                        if (myPlanNodeResponse != null && myPlanNodeResponse.isSuccess()) {
                            apiCallback.onSuccess(myPlanNodeResponse.getMyPlanNodeList());
                        } else
                            apiCallback.onFailure(new Throwable(myPlanNodeResponse.getMessage()));
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

    @Override
    public void getPlan(ApiCallback apiCallback) {
        String token = JWTTokenManager.getInstance().getAccessToken();
        if (token == null) {
            apiCallback.onFailure(new Throwable("세션이 만료되었습니다."));
            return;
        }

        MyPlanRetrofitManager.getInstance().getRetrofit().create(MyPlanService.class).getPlan(MyPlanRetrofitManager.addAuthorizationBearer(token))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyPlanResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyPlanResponse myPlanResponse) {
                        if (myPlanResponse != null && myPlanResponse.isSuccess()) {
                            apiCallback.onSuccess(myPlanResponse.getMyPlanList());
                        } else
                            apiCallback.onFailure(new Throwable(myPlanResponse.getMessage()));
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

    @Override
    public void getPlanWithSelectNode(ApiCallback apiCallback, MyPlanNode myPlanNode) {


        String token = JWTTokenManager.getInstance().getAccessToken();
        if (token == null) {
            apiCallback.onFailure(new Throwable("세션이 만료되었습니다."));
            return;
        }

        List<MyPlan> myPlanList = new ArrayList<>();
        Observable<MyPlanResponse> myPlanResponseObservable = MyPlanRetrofitManager.getInstance().getRetrofit().create(MyPlanService.class).getPlan(MyPlanRetrofitManager.addAuthorizationBearer(token));
        myPlanResponseObservable.flatMapIterable(MyPlanResponse::getMyPlanList)
                .flatMap(myPlan -> getPlanNode(myPlan.getPlanNumber()), ((myPlan, myPlanNodeResponse) -> {
                    if (isContainSameNode(myPlanNodeResponse.getMyPlanNodeList(), myPlanNode))
                        myPlan.setContainPlan(true);
                    else
                        myPlan.setContainPlan(false);
                    myPlan.setMyPlanNodeList(myPlanNodeResponse.getMyPlanNodeList());
                    myPlanList.add(myPlan);
                    return myPlan;
                })).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyPlan>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyPlan myPlan) {

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
                        apiCallback.onSuccess(myPlanList);
                    }
                });

    }

    private boolean isContainSameNode(List<MyPlanNode> myPlanNodeList, MyPlanNode selectMyPlan) {
        for (MyPlanNode myPlanNode : myPlanNodeList) {
            if (myPlanNode.getContendID().equals(selectMyPlan.getContendID()) && myPlanNode.getContentType().equals(selectMyPlan.getContentType()))
                return true;
        }
        return false;
    }

    private Observable<MyPlanNodeResponse> getPlanNode(String planNumber) {
        Map<String, Object> jsonObject = new ArrayMap<>();
        jsonObject.put("plan_no", planNumber);
        String token = JWTTokenManager.getInstance().getAccessToken();
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonObject)).toString());
        return MyPlanRetrofitManager.getInstance().getRetrofit().create(MyPlanService.class).getNode(MyPlanRetrofitManager.addAuthorizationBearer(token), body);
    }

    @Override
    public void updatePlanName(ApiCallback apiCallback, String planNumber, String planTitle) {
        Map<String, Object> jsonObject = new ArrayMap<>();
        jsonObject.put("plan_no", planNumber);
        jsonObject.put("plan_title", planTitle);

        String token = JWTTokenManager.getInstance().getAccessToken();
        if (token == null) {
            apiCallback.onFailure(new Throwable("세션이 만료되었습니다."));
            return;
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonObject)).toString());
        MyPlanRetrofitManager.getInstance().getRetrofit().create(MyPlanService.class).setPlan(MyPlanRetrofitManager.addAuthorizationBearer(token), body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DefaultMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DefaultMessage defaultMessage) {
                        if (defaultMessage != null && defaultMessage.isSuccess()) {
                            apiCallback.onSuccess(defaultMessage);
                        } else
                            apiCallback.onFailure(new Throwable(defaultMessage.getMessage()));
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
