package com.example.mobileprogrammingproject.presenter;

public interface SearchEmailContract {
    interface View{
        void showToast(String message);
    }

    interface Presenter{
        void NABClick();
        void NAPClick();
    }

}
