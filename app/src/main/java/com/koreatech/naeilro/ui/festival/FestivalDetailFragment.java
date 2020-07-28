package com.koreatech.naeilro.ui.festival;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.event.Festival;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.interactor.FestivalRestInteractor;
import com.koreatech.naeilro.ui.festival.presenter.FestivalDetailFragmentPresenter;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.koreatech.naeilro.ui.tourspot.TourSpotDetailFragment.fromHtml;


public class FestivalDetailFragment extends Fragment implements FestivalDetailFragmentContract.View {
    private static final double centerLon = 127.48318433761597;
    private static final double centerLat = 36.41592967015607;

    private View view;
    private FestivalDetailFragmentPresenter festivalDetailFragmentPresenter;
    private Unbinder unbinder;
    private NavController navController;
    private int contentTypeId;
    private int contentId;
    private String tel;
    private String title;
    @BindView(R.id.festival_detail_image)
    ImageView festivalDetailImage;
    @BindView(R.id.festival_detail_title)
    TextView festivalDetailTitle;
    @BindView(R.id.festival_detail_overview)
    TextView festivalDetailOverview;
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
    private TMapView tMapView;
    @BindView(R.id.festival_address_text_view)
    TextView festivalAddressTextView;
    private String addr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_festival_detail, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    public void init(View view) {
        festivalDetailFragmentPresenter = new FestivalDetailFragmentPresenter(new FestivalRestInteractor(), this);
        contentTypeId = getArguments().getInt("contentTypeId");
        contentId = getArguments().getInt("contentId");
        addr = getArguments().getString("address");
        title = getArguments().getString("title");
        tel = getArguments().getString("tel");
        festivalDetailTitle.setText(title);
        initTMap(view);
        festivalDetailFragmentPresenter.getFestivalCommonInfo(contentTypeId, contentId);
        festivalDetailFragmentPresenter.getFestivalIntroInfo(contentTypeId, contentId);

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
        if(unbinder != null) unbinder.unbind();
    }
    @Override
    public void showFestivalCommon(Festival festival) {

        //festivalDetailOverview.setText(setFilter(festival.getOverview()));
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
            if(arrStr[i].equals("&")){
                if (arrStr[i + 1].equals("l") || arrStr[i+1].equals("g")) {
                    if(arrStr[i+2].equals(("t"))){
                        i+=3;
                    }
                }
            }
            if(flag == false){
                filteredString = filteredString.concat(arrStr[i]);
            }
        }
        return  filteredString;
    }
}
