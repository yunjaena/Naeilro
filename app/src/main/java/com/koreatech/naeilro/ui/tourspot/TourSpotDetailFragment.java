package com.koreatech.naeilro.ui.tourspot;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.tour.TourInfo;
import com.koreatech.naeilro.network.interactor.TourRestInteractor;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity;
import com.koreatech.naeilro.ui.tourspot.adapter.TourDetailImageRecyclerViewAdapter;
import com.koreatech.naeilro.ui.tourspot.presenter.TourSpotDetailContract;
import com.koreatech.naeilro.ui.tourspot.presenter.TourSpotDetailPresenter;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_ID;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_TYPE;

public class TourSpotDetailFragment extends Fragment implements TourSpotDetailContract.View {
    private static final double centerLon = 127.48318433761597;
    private static final double centerLat = 36.41592967015607;

    /* View component */
    private ImageView tourDetailImage;
    private TextView tourDetailTitle;
    private TextView tourDetailOverview;
    private TextView tourDetailInfoTextView;
    private TextView tourTelTextView;
    private TextView tourRestDateTextView;
    private TextView tourExperienceDetailTextView;
    private TextView tourExperienceAgeTextView;
    private TextView tourPersonLimitTextView;
    private TextView tourRunningTimeTextView;
    private TextView tourParkingPlaceTextView;
    private TextView tourStrollerRentalTextView;
    private TextView tourPetAvailableTextView;
    private TextView tourCreditCardAvailableTextView;
    private LinearLayout tourImageLinearLayout;
    private ImageView tourExtraImageView;
    private RecyclerView tourExtraImageRecyclerView;
    private LinearLayout tourDetailMapLinearLayout;
    private LinearLayout tourDetailTMapLinearLayout;
    private TextView tourAddressTextView;
    private TMapView tMapView;

    private TourSpotDetailPresenter tourSpotDetailPresenter;
    private LinearLayoutManager linearLayoutManager;
    private List<TourInfo> imageTourInfoList;
    private TourDetailImageRecyclerViewAdapter tourDetailImageRecyclerViewAdapter;
    private int contentId;
    private String contentTypeId;
    private Unbinder unbinder;

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tour_spot_detail, container, false);
        contentId = requireArguments().getInt("contentId");
        this.unbinder = ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @OnClick(R.id.add_my_plan_tour_spot)
    public void clickTourSpotMyPlanButton() {
        Intent intent = new Intent(getActivity(), MyPlanBottomSheetActivity.class);
        intent.putExtra(CONTENT_ID, String.valueOf(contentId));
        intent.putExtra(CONTENT_TYPE, contentTypeId);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }

    private void init(View view) {
        imageTourInfoList = new ArrayList<>();
        initView(view);
        initTMap(view);
        initTourExtraImageRecyclerView();
        tourSpotDetailPresenter = new TourSpotDetailPresenter(this, new TourRestInteractor());
        tourSpotDetailPresenter.getTourCommonInfo(contentId);
        tourSpotDetailPresenter.getTourDetailInfo(contentId);
        tourSpotDetailPresenter.getTourDetailIntroduce(contentId);
        tourSpotDetailPresenter.getTourImageInfo(contentId);
    }

    private void initView(View view) {
        tourDetailImage = view.findViewById(R.id.tour_detail_image);
        tourDetailTitle = view.findViewById(R.id.tour_detail_title);
        tourDetailOverview = view.findViewById(R.id.tour_detail_overview);
        tourTelTextView = view.findViewById(R.id.tour_tel_text_view);
        tourRestDateTextView = view.findViewById(R.id.tour_rest_date);
        tourDetailInfoTextView = view.findViewById(R.id.tour_detail_info_text_view);
        tourExperienceDetailTextView = view.findViewById(R.id.tour_experience_detail_text_view);
        tourExperienceAgeTextView = view.findViewById(R.id.tour_experience_age_text_view);
        tourPersonLimitTextView = view.findViewById(R.id.tour_person_limit_text_view);
        tourRunningTimeTextView = view.findViewById(R.id.tour_running_time_text_view);
        tourParkingPlaceTextView = view.findViewById(R.id.tour_parking_place_text_view);
        tourStrollerRentalTextView = view.findViewById(R.id.tour_stroller_rental_text_view);
        tourPetAvailableTextView = view.findViewById(R.id.tour_pet_available_text_view);
        tourCreditCardAvailableTextView = view.findViewById(R.id.tour_credit_card_available_text_view);
        tourImageLinearLayout = view.findViewById(R.id.tour_image_linear_layout);
        tourExtraImageView = view.findViewById(R.id.tour_extra_image_view);
        tourExtraImageRecyclerView = view.findViewById(R.id.tour_extra_image_recycler_view);
        tourDetailMapLinearLayout = view.findViewById(R.id.tour_detail_map_linear_layout);
        tourDetailTMapLinearLayout = view.findViewById(R.id.tour_detail_t_map_linear_layout);
        tourAddressTextView = view.findViewById(R.id.tour_address_text_view);
        tourDetailMapLinearLayout.setVisibility(View.GONE);
        tourImageLinearLayout.setVisibility(View.GONE);

    }

    private void initTMap(View view) {
        tMapView = new TMapView(Objects.requireNonNull(getActivity()));
        tMapView.setSKTMapApiKey(NaeilroApplication.getTMapApiKey());
        tMapView.setCenterPoint(centerLon, centerLat);
        tourDetailTMapLinearLayout.addView(tMapView);

    }

    public void setAddressInfo(double x, double y, String title, String address) {
        showMapPoint(x, y, title, address);
        tourAddressTextView.setText(address);
    }

    private void showMapPoint(double x, double y, String title, String address) {
        tourDetailMapLinearLayout.setVisibility(View.VISIBLE);
        TMapMarkerItem markerItem = new TMapMarkerItem();
        TMapPoint tMapPoint1 = new TMapPoint(y, x);
        markerItem.setVisible(TMapMarkerItem.VISIBLE);
        markerItem.setPosition(0f, 0f);
        markerItem.setTMapPoint(tMapPoint1);
        markerItem.setName(title);
        markerItem.setCanShowCallout(true);
        markerItem.setCalloutTitle(title);
        markerItem.setCalloutSubTitle(address);
        tMapView.addMarkerItem(title, markerItem);
        tMapView.setCenterPoint(x, y, true);
        tMapView.setZoomLevel(15);
        tMapView.initView();
    }

    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).showProgressDialog(R.string.loading);
    }

    @Override
    public void hideLoading() {
        ((MainActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void showMessage(String message) {
        ToastUtil.getInstance().makeShort(message);
    }

    @Override
    public void showMessage(int message) {
        ToastUtil.getInstance().makeShort(message);
    }

    @Override
    public void showDetailIntroduceList(List<TourInfo> tourItems) {
        setDetailIntroduce(tourItems.get(0));
    }

    @Override
    public void showDetailInfoList(List<TourInfo> tourItems) {
        setDetailInfo(tourItems);
    }

    @Override
    public void showImageInfoList(List<TourInfo> tourItems) {
        setImageExtra(tourItems);
    }

    @Override
    public void showCommonInfo(TourInfo tour) {
        setAddressInfo(Double.parseDouble(tour.getMapx()), Double.parseDouble(tour.getMapy()), tour.getTitle(), tour.getAddr1());
        setFirstImageView(tour.getFirstimage());
        setTitle(tour.getTitle());
        setSummary(tour.getOverview());
    }

    private void setImageExtra(List<TourInfo> tourItems) {
        if (tourItems == null || tourItems.size() == 0) return;
        tourImageLinearLayout.setVisibility(View.VISIBLE);
        imageTourInfoList.addAll(tourItems);
        tourDetailImageRecyclerViewAdapter.notifyDataSetChanged();
        setTourExtraImageView(0);
    }

    private void initTourExtraImageRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        tourDetailImageRecyclerViewAdapter = new TourDetailImageRecyclerViewAdapter(getContext(), imageTourInfoList);
        tourDetailImageRecyclerViewAdapter.setRecyclerViewClickListener(new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Glide.with(getContext())
                        .load(imageTourInfoList.get(position).getOriginimgurl())
                        .error(R.drawable.ic_no_image)
                        .into(tourExtraImageView);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        tourExtraImageRecyclerView.setLayoutManager(linearLayoutManager);
        tourExtraImageRecyclerView.setAdapter(tourDetailImageRecyclerViewAdapter);
    }

    private void setDetailInfo(List<TourInfo> tourItems) {
        StringBuilder stringBuilder = new StringBuilder();
        for (TourInfo tourInfo : tourItems) {
            stringBuilder.append("<b>").append(" · ").append(tourInfo.getInfoname()).append("</b>").append(" : ").append(tourInfo.getInfotext()).append("<br>").append("<br>");
        }
        tourDetailInfoTextView.setText(fromHtml(stringBuilder.toString()));
    }

    private void setDetailIntroduce(TourInfo tourInfo) {
        contentTypeId = tourInfo.getContenttypeid();
        if (tourInfo.getPersonLimitCount() != null)
            tourPersonLimitTextView.setText(fromHtml(tourInfo.getPersonLimitCount()));
        if (tourInfo.getBabyCarriageInvalidate() != null)
            tourStrollerRentalTextView.setText(fromHtml(tourInfo.getBabyCarriageInvalidate()));
        if (tourInfo.getCreditCardInvalidate() != null)
            tourCreditCardAvailableTextView.setText(fromHtml(tourInfo.getCreditCardInvalidate()));
        if (tourInfo.getPetInvaildate() != null)
            tourPetAvailableTextView.setText(fromHtml(tourInfo.getPetInvaildate()));
        if (tourInfo.getExperienceAgeRange() != null)
            tourExperienceAgeTextView.setText(fromHtml(tourInfo.getExperienceAgeRange()));
        if (tourInfo.getExperienceGuide() != null)
            tourExperienceDetailTextView.setText(fromHtml(tourInfo.getExperienceGuide()));
        if (tourInfo.getInfoCenterPhoneNumber() != null)
            tourTelTextView.setText(fromHtml(tourInfo.getInfoCenterPhoneNumber()));
        if (tourInfo.getParkingInvaildate() != null)
            tourParkingPlaceTextView.setText(fromHtml(tourInfo.getParkingInvaildate()));
        if (tourInfo.getRestDate() != null)
            tourRestDateTextView.setText(fromHtml(tourInfo.getRestDate()));
        if (tourInfo.getRunningTime() != null)
            tourRunningTimeTextView.setText(fromHtml(tourInfo.getRunningTime()));
    }

    private void setTourExtraImageView(int position) {
        if (imageTourInfoList.isEmpty() || imageTourInfoList.size() < position) return;
        Glide.with(getContext())
                .load(imageTourInfoList.get(position).getOriginimgurl())
                .error(R.drawable.ic_no_image)
                .into(tourExtraImageView);
    }

    private void setFirstImageView(String url) {
        Glide.with(getContext()).load(url)
                .error(R.drawable.ic_no_image)
                .into(tourDetailImage);
    }

    private void setSummary(String text) {
        if (text == null) return;
        tourDetailOverview.setText(fromHtml(text));
    }

    private void setTitle(String text) {
        if (text == null) return;
        tourDetailTitle.setText(text);
    }

    @Override
    public void setPresenter(TourSpotDetailPresenter presenter) {
        this.tourSpotDetailPresenter = presenter;
    }
}
