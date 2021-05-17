package com.example.mobileprogrammingproject.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.mobileprogrammingproject.database.AppDatabase;
import com.example.mobileprogrammingproject.model.User;
import com.example.mobileprogrammingproject.valueObject.VUser;
import com.example.mobileprogrammingproject.view.MainActivity;
import com.google.firebase.auth.FirebaseUser;
import com.kakao.usermgmt.response.MeV2Response;
import com.example.mobileprogrammingproject.constants.Constants.ELogin;

public class LoginPresenter implements LoginContract.Presenter{

    private Context context;
    private AppDatabase mAppDatabase;
    private LoginContract.View view;

    public LoginPresenter(Context applicationContext, AppDatabase mAppDatabase, LoginContract.View view) {
        this.context = applicationContext;
        this.mAppDatabase = mAppDatabase;
        this.view = view;
    }

    @Override
    public Intent kakakOnSuccess(MeV2Response result) {
        VUser vUser;
        if(validLogin(result.getKakaoAccount().getEmail(), ELogin.intentCheckKakao.getText())==null){
            vUser = signUpKakao(result);
        } else {
            User user = this.mAppDatabase.userDao().findByEmailAndType(result.getKakaoAccount().getEmail(), ELogin.intentCheckKakao.getText());
            vUser = new VUser(user.getId(), user.getPassword(), user.getEmail(), user.getName(), user.getProfileImg(),
                    user.getDateOfBirth(), user.getPhoneNumber(), user.getGender(), user.getEmailType());
        }
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(ELogin.intentName.getText(), vUser.getName());
        intent.putExtra(ELogin.intentEmail.getText(), vUser.getEmail());
        intent.putExtra(ELogin.intentProfileImg.getText(), vUser.getProfileImg());
        intent.putExtra(ELogin.intentCheck.getText(), vUser.getEmailType());
        return intent;
    }

    private User validLogin(String inputEmail, String emailType) {
        User user = mAppDatabase.userDao().findByEmailAndType(inputEmail, emailType);
        return user;
    }

    private VUser signUpKakao(MeV2Response result) {
        User user = new User(null, result.getKakaoAccount().getEmail(), result.getKakaoAccount().getProfile().getNickname(),
                result.getKakaoAccount().getProfile().getProfileImageUrl(), null, null, null, ELogin.intentCheckKakao.getText());
        mAppDatabase.userDao().insert(user);
        VUser vUser = new VUser(user.getId(), user.getPassword(), user.getEmail(), user.getName(), user.getProfileImg(),
                user.getDateOfBirth(), user.getPhoneNumber(), user.getGender(), user.getEmailType());
        this.view.showToast(ELogin.kakaoSignUpMessage.getText());
        return vUser;
    }

    @Override
    public Intent googleOnSuccess(FirebaseUser googleUser) {
        VUser vUser;
        if(validLogin(googleUser.getEmail(), ELogin.intentCheckGoogle.getText())==null){
            vUser = signUpGoogle(googleUser);
        } else{
            User user = this.mAppDatabase.userDao().findByEmail(googleUser.getEmail(), ELogin.intentCheckGoogle.getText());
            vUser = new VUser(user.getId(), user.getPassword(), user.getEmail(), user.getName(), user.getProfileImg(),
                    user.getDateOfBirth(), user.getPhoneNumber(), user.getGender(), user.getEmailType());
        }
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(ELogin.intentName.getText(), vUser.getName());
        intent.putExtra(ELogin.intentEmail.getText(), vUser.getEmail());
        intent.putExtra(ELogin.intentProfileImg.getText(), vUser.getProfileImg());
        intent.putExtra(ELogin.intentCheck.getText(), vUser.getEmailType());
        return intent;
    }

    private VUser signUpGoogle(FirebaseUser googleUser) {
        User user = new User(null, googleUser.getEmail(), googleUser.getDisplayName(),
                String.valueOf(googleUser.getPhotoUrl()), null, null, null, ELogin.intentCheckGoogle.getText());
        mAppDatabase.userDao().insert(user);
        VUser vUser = new VUser(user.getId(), user.getPassword(), user.getEmail(), user.getName(), user.getProfileImg(),
                user.getDateOfBirth(), user.getPhoneNumber(), user.getGender(), user.getEmailType());
        this.view.showToast(ELogin.googleSignUpMessage.getText());
        return vUser;
    }

    @Override
    public Intent appOnSuccess(String email, String password) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(ELogin.intentName.getText(), this.mAppDatabase.userDao().findNameByEmail(email));
        intent.putExtra(ELogin.intentEmail.getText(), email);
        intent.putExtra(ELogin.intentProfileImg.getText(), email);
        intent.putExtra(ELogin.intentCheck.getText(), ELogin.intentCheckApp.getText());
        return intent;
    }

    @Override
    public boolean validLoginCheck(String inputEmail, String inputPassword) {
       User user = mAppDatabase.userDao().findByEmailAndType(inputEmail, ELogin.intentCheckApp.getText());
       if(user!=null){
           if(user.getPassword().equals(inputPassword)){
               this.view.showToast(ELogin.appLoginSuccess.getText());
               return true;
           }else{
               this.view.showToast(ELogin.passwordError.getText());
               return false;
           }
       } else{
           this.view.showToast(ELogin.notValidEmail.getText());
           return false;
       }
    }


}
