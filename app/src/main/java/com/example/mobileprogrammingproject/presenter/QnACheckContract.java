package com.example.mobileprogrammingproject.presenter;

import com.example.mobileprogrammingproject.model.QnA;

import java.util.ArrayList;

public interface QnACheckContract{

    interface View{
        void showToast(String message);
    }
    interface Presenter{

        ArrayList<QnA> getData(String strEmail, String strEmailType);
    }
}
