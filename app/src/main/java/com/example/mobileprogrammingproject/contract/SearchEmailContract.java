package com.example.mobileprogrammingproject.contract;

public interface SearchEmailContract {
    interface View{
        void showToast(String message);
    }

    interface Presenter{
        void NABClick();
        void NAPClick();
    }

}
