package com.koreatech.naeilro.ui.festival;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.event.Festival;
import com.koreatech.naeilro.network.interactor.FestivalRestInteractor;
import com.koreatech.naeilro.ui.festival.presenter.FestivalDetailFragmentPresenter;
import com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import kr.co.prnd.readmore.ReadMoreTextView;

import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_AREA_CODE;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_ID;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_MAP_X;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_MAP_Y;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_THUMBNAIL;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_TITLE;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_TYPE;
import static com.koreatech.naeilro.ui.tourspot.TourSpotDetailFragment.fromHtml;


public class FestivalDetailFragment extends Fragment implements FestivalDetailFragmentContract.View {
    private static final double centerLon = 127.48318433761597;
    private static final double centerLat = 36.41592967015607;
    @BindView(R.id.festival_detail_image)
    ImageView festivalDetailImage;
    @BindView(R.id.festival_detail_title)
    TextView festivalDetailTitle;
    @BindView(R.id.festival_detail_overview)
    ReadMoreTextView festivalDetailOverview;
    @BindView(R.id.festival_info_korean_text_view)
    TextView festivalInfoKoreanTextView;
    @BindView(R.id.festival_info_linear_layout)
    LinearLayout festivalInfoLinearLayout;
    @BindView(R.id.festival_start_date)
    TextView festivalStartDate;
    @BindView(R.id.festival_end_date)
    TextView festivalEndDate;
    @BindView(R.id.festival_playtime)
    TextView festivalPlayTime;
    @BindView(R.id.event_place)
    TextView eventPlace;
    @BindView(R.id.usetimefestival)
    TextView usedCost;
    @BindView(R.id.spendtimefestival)
    TextView spendTimeFestival;
    @BindView(R.id.discountinfofestival)
    TextView discountInfoFestival;
    @BindView(R.id.festival_detail_map_linear_layout)
    LinearLayout festivalDetailMapLinearLayout;
    @BindView(R.id.festival_detail_t_map_linear_layout)
    LinearLayout festivalDetailTMapLinearLayout;
    @BindView(R.id.festival_address_text_view)
    TextView festivalAddressTextView;
    private View view;
    private FestivalDetailFragmentPresenter festivalDetailFragmentPresenter;
    private Unbinder unbinder;
    private NavController navController;
    private int contentTypeId;
    private String contentTypeIdString;
    private int contentId;
    private String contentTitle;
    private String contentThumbnail;
    private String tel;
    private String title;
    private TMapView tMapView;
    private String addr;
    private String mapX;
    private String mapY;
    private String areaCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_festival_detail, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @OnClick(R.id.add_my_plan_festival)
    public void clickFestivalMyPlanButton() {
        Intent intent = new Intent(getActivity(), MyPlanBottomSheetActivity.class);
        intent.putExtra(CONTENT_ID, String.valueOf(contentId));
        intent.putExtra(CONTENT_TYPE, contentTypeIdString);
        intent.putExtra(CONTENT_TITLE, contentTitle);
        intent.putExtra(CONTENT_THUMBNAIL, contentThumbnail);
        intent.putExtra(CONTENT_MAP_X, mapX);
        intent.putExtra(CONTENT_MAP_Y, mapY);
        intent.putExtra(CONTENT_AREA_CODE, areaCode);
        startActivity(intent);
    }

    public void init(View view) {
        festivalDetailFragmentPresenter = new FestivalDetailFragmentPresenter(new FestivalRestInteractor(), this);
        contentTypeId = getArguments().getInt("contentTypeId");
        contentTypeIdString = Integer.toString(contentTypeId);
        contentId = getArguments().getInt("contentId");
        addr = getArguments().getString("address");
        title = getArguments().getString("title");
        tel = getArguments().getString("tel");
        festivalDetailTitle.setText(title);
        initTMap(view);
        festivalDetailFragmentPresenter.getFestivalCommonInfo(contentId);
        festivalDetailFragmentPresenter.getFestivalIntroInfo(contentId);
        festivalInfoKoreanTextView.setOnClickListener(v -> festivalDetailOverview.toggle());
        festivalInfoLinearLayout.setOnClickListener(v -> festivalDetailOverview.toggle());

    }

    private void initTMap(View view) {
        tMapView = new TMapView(Objects.requireNonNull(getActivity()));
        tMapView.setSKTMapApiKey(NaeilroApplication.getTMapApiKey());
        tMapView.setCenterPoint(centerLon, centerLat);
        festivalDetailTMapLinearLayout.addView(tMapView);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }

    @Override
    public void showFestivalCommon(Festival festival) {

        //festivalDetailOverview.setText(setFilter(festival.getOverview()));
        contentTitle = festival.getTitle();
        contentThumbnail = festival.getFirstimage();
        areaCode = festival.getAreacode();
        mapX = festival.getMapx();
        mapY = festival.getMapy();
        setAddressInfo(Double.parseDouble(festival.getMapx()), Double.parseDouble(festival.getMapy()), festival.getTitle(), addr);
        setFirstImageView(festival.getFirstimage());
        setTitle(festival.getTitle());
        setSummary(festival.getOverview());
    }

    public void setAddressInfo(double x, double y, String title, String address) {
        showMapPoint(x, y, title, address);
        festivalAddressTextView.setText(address);
    }

    private void setFirstImageView(String url) {
        Glide.with(getContext()).load(url)
                .apply(new RequestOptions().bitmapTransform(new RoundedCorners(24)))
                .error(R.drawable.ic_no_image)
                .into(festivalDetailImage);
    }

    private void setTitle(String text) {
        if (text == null) return;
        festivalDetailTitle.setText(text);
    }

    private void setSummary(String text) {
        if (text == null) return;
        festivalDetailOverview.setText(fromHtml(text));
        festivalDetailOverview.setChangeListener(this::toggle);
        toggle(festivalDetailOverview.getState());
    }

    private void toggle(ReadMoreTextView.State state){
        if (state == ReadMoreTextView.State.COLLAPSED) {
            festivalInfoKoreanTextView.setVisibility(View.GONE);
            festivalInfoLinearLayout.setVisibility(View.GONE);
        } else {
            festivalInfoKoreanTextView.setVisibility(View.VISIBLE);
            festivalInfoLinearLayout.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void showFestivalIntro(Festival festival) {
        festivalStartDate.setText("   " + festival.getEventstartdate());
        festivalEndDate.setText("   " + festival.getEventenddate());
        if (festival.getPlaytime() == null)
            festivalPlayTime.setText("   -");
        else
            festivalPlayTime.setText("   " + festival.getPlaytime());

        eventPlace.setText("   " + festival.getEventplace());

        if (festival.getUsetimefestival() == null)
            usedCost.setText("   -");
        else
            usedCost.setText("   " + festival.getUsetimefestival());

        if (festival.getSpendtimefestival() == null)
            spendTimeFestival.setText("   -");
        else
            spendTimeFestival.setText("   " + festival.getSpendtimefestival());

        if (festival.getDiscountinfofestival() == null)
            discountInfoFestival.setText("   x");
        else
            discountInfoFestival.setText("   " + festival.getDiscountinfofestival());

    }

    private void showMapPoint(double x, double y, String title, String address) {
        festivalDetailMapLinearLayout.setVisibility(View.VISIBLE);
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
    public void setPresenter(FestivalDetailFragmentPresenter presenter) {
        this.festivalDetailFragmentPresenter = presenter;
    }

    public String setFilter(String overview) {
        String arrStr[] = overview.split("");
        String filteredString = "";
        boolean flag = false;
        String[] filter = {"<", ">", "=", "+", "?", "^", "$", "@", "*", "※", "/", "\\", "&"};
        for (int i = 0; i < arrStr.length; i++) {
            if (arrStr[i].equals("<")) {
                flag = true;
            }
            if (arrStr[i].equals(">")) {
                flag = false;
                continue;
            }
            if (arrStr[i].equals("&")) {
                if (arrStr[i + 1].equals("l") || arrStr[i + 1].equals("g")) {
                    if (arrStr[i + 2].equals(("t"))) {
                        i += 3;
                    }
                }
            }
            if (flag == false) {
                filteredString = filteredString.concat(arrStr[i]);
            }
        }
        return filteredString;
    }
}
