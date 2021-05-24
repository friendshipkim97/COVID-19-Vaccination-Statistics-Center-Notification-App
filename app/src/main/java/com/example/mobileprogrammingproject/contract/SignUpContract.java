package com.example.mobileprogrammingproject.contract;

import android.content.Intent;

import com.example.mobileprogrammingproject.valueObject.VUser;

public interface SignUpContract {
    interface View{
        void showToast(String message);
        void sendUserInfo(Intent intent);
    }

    interface Presenter{
        boolean emailDuplicateCheck(Boolean buttonIsClicked);
        boolean validEmailCheck();
        boolean validPasswordCheck();
        boolean validNameCheck();
        boolean phoneNumberCheck();
        boolean genderCheck();
        Intent signUpCheck();
    }
}
