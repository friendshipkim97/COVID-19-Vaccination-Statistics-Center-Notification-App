package com.example.mobileprogrammingproject.contract;


public interface QnAWriteContract {

    interface View{
        void showToast(String message);
    }
    interface Presenter{

        void submit(String strEmail, String strEmailType);
    }
}
