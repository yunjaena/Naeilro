package com.koreatech.naeilro.network.interactor;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.myplan.MyPlanNode;

public interface MyPlanInteractor {
    void createNode(ApiCallback apiCallback, String planNumber, String contentID, String contentType);

    void createPlan(ApiCallback apiCallback, String planTitle);

    void deleteNode(ApiCallback apiCallback, String nodeNumber);

    void deletePlan(ApiCallback apiCallback, String planNumber);

    void getNode(ApiCallback apiCallback, String planNumber);

    void getPlan(ApiCallback apiCallback);

    void getPlanWithSelectNode(ApiCallback apiCallback, MyPlanNode myPlanNode);

    void updatePlanName(ApiCallback apiCallback, String planNumber, String planTitle);


}
