package com.example.mobileprogrammingproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mobileprogrammingproject.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class MainActivity extends AppCompatActivity {

    // Attributes
    private ActivityMainBinding mBinding;
    private String strNick, strProfileImg, strEmail;
    private LoginStatus loginStatus;
    private GoogleSignInClient googleSignInClient;

    enum LoginStatus{KAKAO, GOOGLE, APP}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        strNick = intent.getStringExtra("name");
        strEmail = intent.getStringExtra("email");
        strProfileImg = intent.getStringExtra("profileImg");
        mBinding.tvCompletedNickName.setText(strNick);
        mBinding.tvCompletedEmail.setText(strEmail);
        Glide.with(this).load(strProfileImg).into(mBinding.ivCompletedImageView);

        if(intent.getStringExtra("check").equals(String.valueOf(loginStatus.KAKAO))){
            kakaoInfoSet(intent);
        } else if(intent.getStringExtra("check").equals(String.valueOf(loginStatus.GOOGLE))){
            googleInfoSet(intent);
        }

    }

    private void kakaoInfoSet(Intent intent) {

        mBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        // 로그아웃 성공시 수행하는 지점
                        finish(); // 현재 액티비티 종료
                    }
                });
            }
        });
    }

    private void googleInfoSet(Intent intent) {

        mBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
//                clearApplicationData();
               finish();
            }
        });
    }

}