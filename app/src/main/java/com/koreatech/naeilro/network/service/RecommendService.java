package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Json;
import com.koreatech.naeilro.network.entity.myplan.MyPlanResponse;
import com.koreatech.naeilro.network.entity.recommend.MyRecommendItem;
import com.koreatech.naeilro.network.entity.recommend.Recommend;

import io.reactivex.Observable;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RecommendService {
    String GET_RECOMMEND = "/nailo/Recommend/recommend.php";



    @POST(GET_RECOMMEND)
    @Json
    Observable<Recommend> getRecommend(@Header("Authorization") String auth);
}
