package com.example.mobileprogrammingproject.contract;

import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;
import com.kakao.usermgmt.response.MeV2Response;

public interface LoginContract {
    interface View{
        void showToast(String message);
    }
    interface Presenter{
        Intent kakakOnSuccess(MeV2Response result);
        Intent googleOnSuccess(FirebaseUser user);
        Intent appOnSuccess(String email, String password);
        boolean validLoginCheck(String inputEmail, String inputPassword);
    }
}
