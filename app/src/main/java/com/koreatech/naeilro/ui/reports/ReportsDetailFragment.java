package com.koreatech.naeilro.ui.reports;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.reports.Reports;
import com.koreatech.naeilro.network.interactor.ReportsRestInteractor;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.reports.adapter.ReportsDetailInfoRecyclerViewAdapter;
import com.koreatech.naeilro.ui.reports.adapter.ReportsImageRecyclerViewAdapter;
import com.koreatech.naeilro.ui.reports.presenter.ReportsDetailFragmentPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ReportsDetailFragment extends Fragment implements ReportsDetailFragmentContract.View {
    private View view;
    private ReportsDetailFragmentPresenter reportsDetailFragmentPresenter;
    private RecyclerView reportsDetailRecyclerView;
    private RecyclerView reportsImageRecyclerView;
    private ReportsDetailInfoRecyclerViewAdapter reportsDetailInfoRecyclerViewAdapter;
    private ReportsImageRecyclerViewAdapter reportsImageRecyclerViewAdapter;
    private int contentId;
    private Unbinder unbinder;
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reports_detail, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        init(view);
        reportsDetailFragmentPresenter.getComonInfo(contentId);
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
        reportsDetailFragmentPresenter.getComonInfo(contentId);

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
        if (reports.getFirstimage() == null) {
            Glide.with(reportsImageView).load(R.drawable.ic_no_image).into(reportsImageView);
        } else {
            Glide.with(reportsImageView).load(reports.getFirstimage()).into(reportsImageView);
        }
        reportsDetailTitle.setText(reports.getTitle());
        reportsDetailOverview.setText("소개 : "+ fromHtml(reports.getOverview()));
        if (reports.getTel() == null)
            reportsDetailTel.setVisibility(View.GONE);
        else
            reportsDetailTel.setText("전화번호 : " + reports.getTel());
        if (reports.getAddr1() == null)
            reportsDetailAddress.setVisibility(View.GONE);
        else
            reportsDetailAddress.setText("주소 : " + reports.getAddr1());
        reportsDetailFragmentPresenter.getDetailInfo(contentId);


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
