package com.example.mobileprogrammingproject.presenter;

public interface SignUpContract {
    interface View{
        void showToast(String message);
    }

    interface Presenter{
        boolean emailDuplicateCheck(Boolean buttonIsClicked);
        boolean validEmailCheck();
        boolean validPasswordCheck();
        boolean validNameCheck();
        boolean phoneNumberCheck();
        boolean genderCheck();
        boolean signUpCheck();
    }
}
