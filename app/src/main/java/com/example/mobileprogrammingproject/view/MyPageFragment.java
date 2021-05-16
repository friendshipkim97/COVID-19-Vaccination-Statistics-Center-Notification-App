package com.example.mobileprogrammingproject.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.mobileprogrammingproject.databinding.ActivityMainBinding;
import com.example.mobileprogrammingproject.databinding.FragmentMyPageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class MyPageFragment extends Fragment {

    private View view;
    private FragmentMyPageBinding mBinding;
    private String strNick, strProfileImg, strEmail, strEmailType;
    private Context context;
    enum loginStatus{KAKAO, GOOGLE, APP};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // fragment는 onCreateView로 생성하면 된다.

        mBinding = FragmentMyPageBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();
        Bundle bundle = getArguments();
        strNick = bundle.getString("strNick");
        strProfileImg = bundle.getString("strProfileImg");
        strEmail = bundle.getString("strEmail");
        strEmailType = bundle.getString("strEmailType");

        mBinding.tvCompletedNickName.setText(strNick);
        mBinding.tvCompletedEmail.setText(strEmail);
        Glide.with(this).load(strProfileImg).override(500,500).into(mBinding.ivCompletedImageView);
        if(strEmailType.equals(String.valueOf(loginStatus.KAKAO))){
            kakaoLogout();
        } else if(strEmailType.equals(String.valueOf(loginStatus.GOOGLE))){
            googleLogout();
        } else if(strEmailType.equals(String.valueOf(loginStatus.APP))){
            appLogout();
        }
        return view;
    }

    private void kakaoLogout() {
        mBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        showToast("로그아웃 했습니다.");
                        getActivity().finish();
                    }
                });
            }
        });
    }

    private void googleLogout() {
        mBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                showToast("로그아웃 했습니다.");
                getActivity().finish();
            }
        });
    }

    private void appLogout() {
        mBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("로그아웃 했습니다.");
                getActivity().finish();
            }
        });
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
