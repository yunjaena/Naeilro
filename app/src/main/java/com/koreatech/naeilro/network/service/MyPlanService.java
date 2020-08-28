package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Json;
import com.koreatech.naeilro.network.entity.DefaultMessage;
import com.koreatech.naeilro.network.entity.myplan.MyPlanNodeResponse;
import com.koreatech.naeilro.network.entity.myplan.MyPlanResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MyPlanService {
    String CREATE_NODE = "/edit/html/nailo/place/create_node.php";
    String CREATE_PLAN = "/edit/html/nailo/place/create_plan.php";
    String DROP_NODE = "/edit/html/nailo/place/drop_node.php";
    String DROP_PLAN = "/edit/html/nailo/place/drop_plan.php";
    String GET_NODE = "/edit/html/nailo/place/get_node.php";
    String GET_PLAN = "/edit/html/nailo/place/get_plan.php";
    String SET_PLAN = "/edit/html/nailo/place/set_plan.php";

    @POST(CREATE_NODE)
    @Json
    Observable<DefaultMessage> createNode(@Header("Authorization") String auth, @Body RequestBody requestBody);

    @POST(CREATE_PLAN)
    @Json
    Observable<DefaultMessage> createPlan(@Header("Authorization") String auth, @Body RequestBody requestBody);

    @POST(DROP_NODE)
    @Json
    Observable<DefaultMessage> dropNode(@Header("Authorization") String auth, @Body RequestBody requestBody);

    @POST(DROP_PLAN)
    @Json
    Observable<MyPlanResponse> dropPlan(@Header("Authorization") String auth, @Body RequestBody requestBody);

    @POST(GET_NODE)
    @Json
    Observable<MyPlanNodeResponse> getNode(@Header("Authorization") String auth, @Body RequestBody requestBody);

    @POST(GET_PLAN)
    @Json
    Observable<DefaultMessage> getPlan(@Header("Authorization") String auth, @Body RequestBody requestBody);

    @POST(SET_PLAN)
    @Json
    Observable<DefaultMessage> setPlan(@Header("Authorization") String auth, @Body RequestBody requestBody);
}
