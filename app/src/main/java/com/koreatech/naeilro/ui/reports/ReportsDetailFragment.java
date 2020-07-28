package com.koreatech.naeilro.ui.reports;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.reports.Reports;
import com.koreatech.naeilro.network.interactor.ReportsRestInteractor;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.reports.adapter.ReportsDetailInfoRecyclerViewAdapter;
import com.koreatech.naeilro.ui.reports.adapter.ReportsImageRecyclerViewAdapter;
import com.koreatech.naeilro.ui.reports.presenter.ReportsDetailFragmentPresenter;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.koreatech.naeilro.ui.tourspot.TourSpotDetailFragment.fromHtml;

public class ReportsDetailFragment extends Fragment implements ReportsDetailFragmentContract.View {
    private View view;
    private static final double centerLon = 127.48318433761597;
    private static final double centerLat = 36.41592967015607;

    private ReportsDetailFragmentPresenter reportsDetailFragmentPresenter;
    private RecyclerView reportsDetailRecyclerView;
    private RecyclerView reportsImageRecyclerView;
    private ReportsDetailInfoRecyclerViewAdapter reportsDetailInfoRecyclerViewAdapter;
    private ReportsImageRecyclerViewAdapter reportsImageRecyclerViewAdapter;
    private int contentId;
    private Unbinder unbinder;
    private String title;
    @BindView(R.id.reports_detail_title)
    TextView reportsDetailTitle;
    @BindView(R.id.reports_detail_overview)
    TextView reportsDetailOverview;
    @BindView(R.id.reports_detail_image)
    ImageView reportsImageView;
    @BindView(R.id.reports_detail_tel)
    TextView reportsDetailTel;
    @BindView(R.id.reports_detail_address)
    TextView reportsDetailAddress;
    @BindView(R.id.reports_detail_map_linear_layout)
    LinearLayout reportsDetailMapLinearLayout;
    @BindView(R.id.reports_detail_t_map_linear_layout)
    LinearLayout reportsDetailTMapLinearLayout;
    private TMapView tMapView;
    @BindView(R.id.reports_address_text_view)
    TextView reportsAddressTextView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reports_detail, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        init(view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }

    public void init(View view) {
        reportsDetailFragmentPresenter = new ReportsDetailFragmentPresenter(new ReportsRestInteractor(), this);
        contentId = getArguments().getInt("contentId");
        title = getArguments().getString("title");
        initTMap(view);
        reportsDetailFragmentPresenter.getComonInfo(contentId);
        reportsDetailFragmentPresenter.getDetailInfo(contentId);

    }
    private void initTMap(View view) {
        tMapView = new TMapView(Objects.requireNonNull(getActivity()));
        tMapView.setSKTMapApiKey(NaeilroApplication.getTMapApiKey());
        tMapView.setCenterPoint(centerLon, centerLat);
        reportsDetailTMapLinearLayout.addView(tMapView);

    }

    @Override
    public void showDetailInfoList(List<Reports> reportsList) {
        reportsDetailRecyclerView = view.findViewById(R.id.reports_detailInfo_recyclerview);
        reportsDetailRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        reportsDetailInfoRecyclerViewAdapter = new ReportsDetailInfoRecyclerViewAdapter(reportsList);
        reportsDetailRecyclerView.setAdapter(reportsDetailInfoRecyclerViewAdapter);
        reportsDetailFragmentPresenter.getImageInfo(contentId);
    }

    @Override
    public void showImageInfoList(List<Reports> reportsList) {
        reportsImageRecyclerView = view.findViewById(R.id.reports_Image_recyclerview);
        reportsImageRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        reportsImageRecyclerViewAdapter = new ReportsImageRecyclerViewAdapter(reportsList);
        reportsImageRecyclerView.setAdapter(reportsImageRecyclerViewAdapter);

    }

    @Override
    public void showCommonInfo(Reports reports) {
        setAddressInfo(Double.parseDouble(reports.getMapx()), Double.parseDouble(reports.getMapy()), title, reports.getAddr1());
        setFirstImageView(reports.getFirstimage());
        setTitle(title);
        setSummary(reports.getOverview());



    }
    private void showMapPoint(double x, double y, String title, String address) {
        reportsDetailMapLinearLayout.setVisibility(View.VISIBLE);
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
    public void setAddressInfo(double x, double y, String title, String address) {
        showMapPoint(x, y, title, address);
        reportsAddressTextView.setText(address);
    }
    private void setFirstImageView(String url) {
        Glide.with(getContext()).load(url)
                .error(R.drawable.ic_no_image)
                .into(reportsImageView);
    }
    private void setTitle(String text) {
        if (text == null) return;
        reportsDetailTitle.setText(text);
    }
    private void setSummary(String text) {
        if (text == null) return;
        reportsDetailOverview.setText(fromHtml(text));
    }

    public static Spanned fromHtml(String source) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(source);
        }
        return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
    }

    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).showProgressDialog(R.string.loading_reports_info);
    }

    @Override
    public void hideLoading() {
        ((MainActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void setPresenter(ReportsDetailFragmentPresenter presenter) {
        this.reportsDetailFragmentPresenter = presenter;
    }
}
