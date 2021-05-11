package com.example.mobileprogrammingproject.presenter;

import android.util.Log;

import com.example.mobileprogrammingproject.constants.Constants;
import com.example.mobileprogrammingproject.dao.AppDatabase;
import com.example.mobileprogrammingproject.databinding.ActivitySearchPasswordBinding;
import com.example.mobileprogrammingproject.gMailSender.GMailSender;
import com.example.mobileprogrammingproject.constants.Constants.ESearchPasswordPresenter;

import java.util.List;
import java.util.regex.Pattern;

public class SearchPasswordPresenter implements SearchPasswordContract.Presenter{

    // Attributes
    private ActivitySearchPasswordBinding mBinding;
    private AppDatabase mAppDatabase;
    private SearchPasswordContract.View searchPasswordView;

    //Constructor
    public SearchPasswordPresenter(SearchPasswordContract.View searchPasswordView, ActivitySearchPasswordBinding mBinding,AppDatabase mAppDatabase) {
        this.mAppDatabase = mAppDatabase;
        this.searchPasswordView = searchPasswordView;
        this.mBinding = mBinding;
    }

    @Override
    public void sendGmail(String recipients) {
        if(emailDuplicateCheck(false, recipients)==true) {
            String findPassword = mAppDatabase.userDao().findPasswordByEmail(recipients);
            try {
                GMailSender sender = new GMailSender(ESearchPasswordPresenter.user.getText(), ESearchPasswordPresenter.password.getText());
                sender.sendMail(ESearchPasswordPresenter.mailTitle.getText(),
                        ESearchPasswordPresenter.mailContents1.getText() + findPassword + ESearchPasswordPresenter.mailContents2.getText(),
                        ESearchPasswordPresenter.user.getText(),
                        recipients);
                searchPasswordView.showToast(ESearchPasswordPresenter.completedSendMail.getText());
            } catch (Exception e) {
                Log.e(ESearchPasswordPresenter.mailExceptionMessage.getText(), e.getMessage(), e);
            }
        }
    }

    public boolean emailDuplicateCheck(Boolean buttonIsClicked, String recipients){
        boolean emailDuplicateResult = false;
        if(validEmailCheck(recipients)==false){
            return false;
        } else if(validEmailCheck(recipients)==true){
            List<String> allEmail = mAppDatabase.userDao().findAllEmail();
            for (String email : allEmail) {
                if (email.equals(recipients)) {
                    if (mAppDatabase.userDao().findTypeByEmail(email).equals("app")) {
                        emailDuplicateResult = true;
                    }
                }
            }
            if (emailDuplicateResult == false) {
                searchPasswordView.showToast(ESearchPasswordPresenter.notValidEmail.getText());
                    return emailDuplicateResult;
                }
            }
        return emailDuplicateResult;
    }

    public boolean validEmailCheck(String recipients){
        Boolean emailFormatResult = Pattern.matches(ESearchPasswordPresenter.emailFormat.getText(), recipients);
        if(emailFormatResult==false){
            searchPasswordView.showToast(ESearchPasswordPresenter.emailFormatErrorMessage.getText());
            return false;
        }
        if(recipients.length()<10 || recipients.length()>25){
            searchPasswordView.showToast(ESearchPasswordPresenter.emailLetterCountError.getText());
            return false;
        }

        return true;
    }
}
