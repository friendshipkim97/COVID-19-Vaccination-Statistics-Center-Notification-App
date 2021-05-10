package com.example.mobileprogrammingproject.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.mobileprogrammingproject.view.LoginActivity;
import com.example.mobileprogrammingproject.view.MainActivity;
import com.google.firebase.auth.FirebaseUser;
import com.kakao.usermgmt.response.MeV2Response;
import com.example.mobileprogrammingproject.constants.Constants.ELogin;

public class LoginPresenter implements LoginContract.Presenter{

    private Context context;

    public LoginPresenter(Context applicationContext) {
        this.context = applicationContext;
    }

    @Override
    public Intent kakakOnSuccess(MeV2Response result) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(ELogin.intentName.getText(), result.getKakaoAccount().getProfile().getNickname());
        intent.putExtra(ELogin.intentEmail.getText(), result.getKakaoAccount().getEmail());
        intent.putExtra(ELogin.intentProfileImg.getText(), result.getKakaoAccount().getProfile().getProfileImageUrl());
        intent.putExtra(ELogin.intentCheck.getText(), ELogin.intentCheckKakao.getText());
        return intent;
    }

    @Override
    public Intent googleOnSuccess(FirebaseUser user) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(ELogin.intentName.getText(), user.getDisplayName());
        intent.putExtra(ELogin.intentEmail.getText(), user.getEmail());
        intent.putExtra(ELogin.intentProfileImg.getText(), String.valueOf(user.getPhotoUrl()));
        intent.putExtra(ELogin.intentCheck.getText(), ELogin.intentCheckGoogle.getText());
        return intent;
    }

}
