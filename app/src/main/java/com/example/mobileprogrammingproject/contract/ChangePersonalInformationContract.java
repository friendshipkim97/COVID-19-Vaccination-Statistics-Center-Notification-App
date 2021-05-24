package com.example.mobileprogrammingproject.contract;

public interface ChangePersonalInformationContract{

    interface View{
        void showToast(String message);
    }
    interface Presenter{

        boolean changePersonalInformation(String strEmail, String strEmailType);
    }
}
