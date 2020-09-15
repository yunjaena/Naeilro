package com.koreatech.naeilro.ui.myinfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.koreatech.naeilro.R;
import com.koreatech.naeilro.auth.JWTTokenManager;
import com.koreatech.naeilro.network.entity.user.User;
import com.koreatech.naeilro.network.entity.user.UserInfo;
import com.koreatech.naeilro.network.interactor.UserRestInteractor;
import com.koreatech.naeilro.ui.login.LoginActivity;
import com.koreatech.naeilro.ui.myinfo.presenter.MyInfoContract;
import com.koreatech.naeilro.ui.myinfo.presenter.MyInfoPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MyInfoFragment extends Fragment implements MyInfoContract.View {
    private Button logoutButton;
    private TextView myInfoNameTextView;
    private Button deactivateAccountButton;
    private TextView myEmailTextView;
    private View view;
    private Unbinder unbinder;
    private MyInfoPresenter myInfoPresenter;
    private String token;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_info, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        init(view);
        token = JWTTokenManager.getInstance().getAccessToken();
        myInfoPresenter.getUserInfo(token);

        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder != null) unbinder.unbind();
    }
    public void init(View view){
        navController =  Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        logoutButton = view.findViewById(R.id.log_out);
        myInfoNameTextView = view.findViewById(R.id.myinfo_name);
        myEmailTextView = view.findViewById(R.id.myinfo_email);
        deactivateAccountButton = view.findViewById(R.id.deactivate_account_button);
        myInfoPresenter = new MyInfoPresenter(new UserRestInteractor(), this);
    }
    @OnClick(R.id.log_out)
    public void clickLogOutButton(){
        myInfoPresenter.logOut(token);

    }

    @OnClick(R.id.user_password_change_button)
    public void onClickedChangePasswordButton(View view){
        String email = myEmailTextView.getText().toString();
        String name = myInfoNameTextView.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("email",email);
        bundle.putString("name",name);
        navController.navigate(R.id.action_navigation_my_info_to_navigation_change_password, bundle);
    }

    @OnClick(R.id.deactivate_account_button)
    public void onClickedDeactivateAccountButton(View view){
        myInfoPresenter.deactivateAccount(token);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLogOut(UserInfo userInfo) {
        if(userInfo.getSuccess() == 1) {
            Toast.makeText(getActivity(), userInfo.getMessage(), Toast.LENGTH_LONG).show();
            JWTTokenManager.getInstance().deleteAuth();
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        }
        else{
            Toast.makeText(getActivity(), R.string.logout_fail_message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showInfo(UserInfo userInfo) {
        User user = userInfo.getUser();
        myInfoNameTextView.setText(user.getName());
        myEmailTextView.setText(user.getEmail());
    }

    @Override
    public void showDeactivateAccount(UserInfo userInfo) {
        if(userInfo.getSuccess() == 1) {
            Toast.makeText(getActivity(), userInfo.getMessage(), Toast.LENGTH_LONG).show();
            JWTTokenManager.getInstance().deleteAuth();
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        }
        else{
            Toast.makeText(getActivity(), R.string.deactivate_fail_message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setPresenter(MyInfoPresenter presenter) {
        this.myInfoPresenter = presenter;
    }
}
