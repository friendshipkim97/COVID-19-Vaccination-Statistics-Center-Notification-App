package com.example.mobileprogrammingproject.presenter;


public interface SearchPasswordContract {
    interface View{
        void showToast(String message);
    }
    interface Presenter{

        void sendGmail(String recipients);
    }
}
